package ru.webarmour.weatercompose.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.webarmour.weatercompose.data.network.api.ApiFactory
import ru.webarmour.weatercompose.data.network.api.ApiService
import ru.webarmour.weatercompose.presentation.ui.theme.WeaterComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = ApiFactory.apiService

        CoroutineScope(Dispatchers.Main).launch{
            try {
                val currentWeather = apiService.loadCurrentWeather("London")
                val forecast = apiService.loadForecast("London")
                val cities = apiService.searchCity("London")
                Log.d("MainActivity", "CurrentWeather:$currentWeather\nForecast:$forecast\nCities:$cities")
            } catch (e:Exception){
                Log.d("MainActivity", "${e}")
            }

        }

        setContent {
            WeaterComposeTheme {

            }
        }
    }
}
