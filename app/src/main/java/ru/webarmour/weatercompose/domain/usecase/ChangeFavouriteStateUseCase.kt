package ru.webarmour.weatercompose.domain.usecase

import ru.webarmour.weatercompose.domain.entity.City
import ru.webarmour.weatercompose.domain.repository.FavouriteRepository

class ChangeFavouriteStateUseCase(
   val repository: FavouriteRepository
) {

    suspend fun addCityToFavourites(city: City) = repository.addToFavourite(city)

    suspend fun removeCityFromFavourites(cityId: Int) = repository.removeFromFavourite(cityId)

}