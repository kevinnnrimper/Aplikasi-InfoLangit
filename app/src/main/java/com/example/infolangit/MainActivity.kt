package com.example.infolangit

import com.example.infolangit.WeatherService
import com.example.infolangit.WeatherViewModel
import com.example.infolangit.WeatherScreen
import androidx.compose.foundation.Image
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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "background",
            Modifier.padding(innerPadding)
        ) {
            composable("background") { BackgroundScreen() }
            composable("weather") { WeatherScreen() }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.ic_home), contentDescription = "Home") },
            label = { Text("Home") },
            selected = false,
            onClick = { navController.navigate("background") }
        )
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.ic_weather), contentDescription = "Weather") },
            label = { Text("Weather") },
            selected = false,
            onClick = { navController.navigate("weather") }
        )
    }
}

@Composable
fun BackgroundScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_biru),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Selamat Datang di Aplikasi InfoLangit",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Jelajahi informasi langit dengan mudah dan cepat!",
                color = Color.White
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewBackgroundScreen() {
    BackgroundScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviewWeatherScreen() {
    WeatherScreen()
}
