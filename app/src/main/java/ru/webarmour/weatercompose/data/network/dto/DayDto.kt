package ru.webarmour.weatercompose.data.network.dto

import com.google.gson.annotations.SerializedName

data class DayDto(

    @SerializedName("date_epoch") val date: Long,
    @SerializedName("day") val conditionDto: DayWeatherDto,
)
