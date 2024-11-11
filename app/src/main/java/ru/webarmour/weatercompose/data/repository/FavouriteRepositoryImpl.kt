package ru.webarmour.weatercompose.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.webarmour.weatercompose.data.local.db.FavouriteCitiesDao
import ru.webarmour.weatercompose.data.mapper.toCity
import ru.webarmour.weatercompose.data.mapper.toDbModel
import ru.webarmour.weatercompose.domain.entity.City
import ru.webarmour.weatercompose.domain.repository.FavouriteRepository
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val dao: FavouriteCitiesDao,

    ) : FavouriteRepository {
    override val favouriteCities: Flow<List<City>> = dao.getFavouriteCities()
        .map { it.toCity() }

    override fun observeIsFavourite(cityId: Int): Flow<Boolean> = dao.observeIsFavourite(cityId)

    override suspend fun addToFavourite(city: City) = dao.addToFavourite(city.toDbModel())

    override suspend fun removeFromFavourite(cityId: Int) = dao.removeFromFavourite(cityId)

}