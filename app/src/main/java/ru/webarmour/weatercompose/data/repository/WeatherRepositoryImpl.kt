package ru.webarmour.weatercompose.data.repository

import ru.webarmour.weatercompose.data.mapper.toWeather
import ru.webarmour.weatercompose.data.mapper.toWeatherForecast
import ru.webarmour.weatercompose.data.network.api.ApiService
import ru.webarmour.weatercompose.domain.entity.Forecast
import ru.webarmour.weatercompose.domain.entity.Weather
import ru.webarmour.weatercompose.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : WeatherRepository {

    override suspend fun getWeather(cityId: Int): Weather {
        return apiService.loadCurrentWeather("$PREFIX_CITY_ID$cityId").toWeather()
    }

    override suspend fun getForecast(cityId: Int): Forecast {
        return apiService.loadForecast("$PREFIX_CITY_ID$cityId").toWeatherForecast()
    }


    private companion object {
        private const val PREFIX_CITY_ID = "id:"
    }
}