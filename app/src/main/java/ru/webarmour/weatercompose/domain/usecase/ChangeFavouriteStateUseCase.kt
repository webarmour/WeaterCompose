package ru.webarmour.weatercompose.domain.usecase

import ru.webarmour.weatercompose.domain.entity.City
import ru.webarmour.weatercompose.domain.repository.FavouriteRepository
import javax.inject.Inject

class ChangeFavouriteStateUseCase @Inject constructor(
    private val repository: FavouriteRepository,
) {

    suspend fun addCityToFavourites(city: City) = repository.addToFavourite(city)

    suspend fun removeCityFromFavourites(cityId: Int) = repository.removeFromFavourite(cityId)

}