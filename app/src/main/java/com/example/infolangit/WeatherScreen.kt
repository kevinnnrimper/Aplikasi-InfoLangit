package com.example.infolangit

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = viewModel()) {
    val apiKey = "8648b14ae3900934180b9392c8e477c4" // Ganti dengan API Key OpenWeatherMap
    var weatherData by remember { mutableStateOf<WeatherResponse?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Fetch Data Cuaca
    LaunchedEffect(Unit) {
        viewModel.fetchWeather("Manado", apiKey) { response, error ->
            weatherData = response
            errorMessage = error
            isLoading = false
            // Tambahkan Log untuk debug
            if (response != null) {
                Log.d("WeatherScreen", "Data berhasil dimuat: $response")
            } else {
                Log.e("WeatherScreen", "Gagal memuat data: $error")
            }
        }
    }

    // Tampilan UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4A90E2)),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = Color.White)
        } else if (errorMessage != null) {
            Text(
                text = "Error: $errorMessage",
                color = Color.Red,
                fontSize = 18.sp
            )
        } else {
            weatherData?.let { data ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(0.9f)
                ) {
                    Column(
                        modifier = Modifier
                            .background(Color(0xFF2C5A84))
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Judul Hari
                        Text("Minggu", fontSize = 18.sp, color = Color.White)

                        // Suhu dan Icon
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(R.drawable.ic_sun), // Replace with your icon
                                contentDescription = "Weather Icon",
                                modifier = Modifier.size(48.dp),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = "${data.main.temp.toInt()}°C",
                                fontSize = 48.sp,
                                color = Color.White
                            )
                        }

                        // Kondisi Cuaca
                        Text(
                            text = data.weather.firstOrNull()?.main ?: "Tidak diketahui",
                            fontSize = 18.sp,
                            color = Color.White
                        )

                        // Nama Lokasi
                        Text(
                            text = data.name,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }
            } ?: Text("Gagal memuat data cuaca!", color = Color.Red, fontSize = 18.sp)
        }
    }
}


