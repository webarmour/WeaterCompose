package ru.webarmour.weatercompose.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.webarmour.weatercompose.domain.entity.City

interface FavouriteRepository {

    val favouriteCities: Flow<City>

    fun observeIsFavourite(cityId: Int): Flow<Boolean>

    suspend fun addToFavourite(city: City)

    suspend fun removeFromFavourite(cityId: Int)

}