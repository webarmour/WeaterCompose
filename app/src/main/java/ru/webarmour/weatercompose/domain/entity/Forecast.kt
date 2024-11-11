package ru.webarmour.weatercompose.domain.entity

data class Forecast(
    val currentWeather: Weather,
    val upcoming: List<Weather>
)
