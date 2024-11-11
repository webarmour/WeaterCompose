package ru.webarmour.weatercompose.data.repository

import ru.webarmour.weatercompose.data.mapper.toCity
import ru.webarmour.weatercompose.data.network.api.ApiService
import ru.webarmour.weatercompose.domain.entity.City
import ru.webarmour.weatercompose.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : SearchRepository {

    override suspend fun search(query: String): List<City> =
        apiService.searchCity(query).toCity()
}