package ru.webarmour.weatercompose.data.network.dto

import com.google.gson.annotations.SerializedName

data class WeatherCurrentDto(
    @SerializedName("current") val currentDto: WeatherDto
)