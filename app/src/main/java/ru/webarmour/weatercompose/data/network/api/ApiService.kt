package ru.webarmour.weatercompose.data.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.webarmour.weatercompose.data.network.dto.CityDto
import ru.webarmour.weatercompose.data.network.dto.WeatherCurrentDto
import ru.webarmour.weatercompose.data.network.dto.WeatherForecastDto

interface ApiService {


    @GET("current.json?key=b90cee7bb4164074889105445241111")
    suspend fun loadCurrentWeather(
        @Query("q") query: String,
    ): WeatherCurrentDto

    @GET("forecast.json?key=b90cee7bb4164074889105445241111")
    suspend fun loadForecast(
        @Query("q") query: String,
        @Query("days") daysCount: Int = 4,
    ): WeatherForecastDto

    @GET("search.json?key=b90cee7bb4164074889105445241111")
    suspend fun searchCity(
        @Query("q") query: String,
    ): List<CityDto>


}