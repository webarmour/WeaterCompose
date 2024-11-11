package ru.webarmour.weatercompose.domain.repository

import ru.webarmour.weatercompose.domain.entity.City

interface SearchRepository {




    suspend fun search(query: String): List<City>
}