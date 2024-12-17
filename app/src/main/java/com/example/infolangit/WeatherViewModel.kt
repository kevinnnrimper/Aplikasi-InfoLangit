package com.example.infolangit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherViewModel : ViewModel() {
    private val weatherService: WeatherService
    var weatherResponse: WeatherResponse? = null
        private set

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        weatherService = retrofit.create(WeatherService::class.java)
    }

    fun fetchWeather(city: String, apiKey: String, onResult: (WeatherResponse?, String?) -> Unit) {
        viewModelScope.launch {
            try {
                val response = weatherService.getWeather(city, apiKey)
                onResult(response, null)
            } catch (e: Exception) {
                onResult(null, e.message ?: "Terjadi kesalahan")
            }
        }
    }
}
