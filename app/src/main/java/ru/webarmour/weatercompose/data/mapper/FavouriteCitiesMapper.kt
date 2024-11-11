package ru.webarmour.weatercompose.data.mapper

import ru.webarmour.weatercompose.data.local.model.CityDbModel
import ru.webarmour.weatercompose.domain.entity.City


fun City.toDbModel():CityDbModel = CityDbModel(id, name, country)

fun CityDbModel.toCity():City = City(id, name, country)


fun List<CityDbModel>.toCity():List<City> = this.map {
    it.toCity()
}

