package ru.webarmour.weatercompose.data.mapper

import ru.webarmour.weatercompose.data.network.dto.DayDto
import ru.webarmour.weatercompose.data.network.dto.WeatherCurrentDto
import ru.webarmour.weatercompose.data.network.dto.WeatherDto
import ru.webarmour.weatercompose.data.network.dto.WeatherForecastDto
import ru.webarmour.weatercompose.domain.entity.Forecast
import ru.webarmour.weatercompose.domain.entity.Weather
import java.util.Calendar
import java.util.Date


fun WeatherCurrentDto.toWeather(): Weather = currentDto.toWeather()

fun WeatherDto.toWeather(): Weather = Weather(
    tempC = tempC,
    conditionText = conditionDto.text,
    conditionUrl = conditionDto.iconUrl.correctImageUrl(),
    date = date.toCalendar()
)

fun WeatherForecastDto.toWeatherForecast():Forecast {
    return Forecast(
        currentWeather = current.toWeather(),
        upcoming = forecastDto.forecastDay.drop(1).map {dayDto ->
            val dayWeatherDto = dayDto.conditionDto
            Weather(
                tempC = dayWeatherDto.tempC,
                conditionText = dayWeatherDto.conditionDto.text,
                conditionUrl = dayWeatherDto.conditionDto.iconUrl.correctImageUrl(),
                date = dayDto.date.toCalendar()
            )
        }
    )
}

private fun Long.toCalendar() = Calendar.getInstance().apply {
    time = Date(this@toCalendar * 1000)
}

private fun String.correctImageUrl() = "https:${this.replace("64x64", "128x128")}"
