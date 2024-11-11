package ru.webarmour.weatercompose.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.webarmour.weatercompose.domain.entity.City
import ru.webarmour.weatercompose.domain.entity.Forecast
import ru.webarmour.weatercompose.domain.entity.Weather

interface WeatherRepository {




    suspend fun getWeather(cityId: Int): Weather

    suspend fun getForecast(cityId: Int): Forecast



}