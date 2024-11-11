package ru.webarmour.weatercompose.data.mapper

import ru.webarmour.weatercompose.data.network.dto.CityDto
import ru.webarmour.weatercompose.domain.entity.City


fun CityDto.toCity():City = City(id, name, country)

fun List<CityDto>.toCity():List<City> = this.map { it.toCity() }