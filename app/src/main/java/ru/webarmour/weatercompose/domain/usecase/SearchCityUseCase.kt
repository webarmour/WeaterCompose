package ru.webarmour.weatercompose.domain.usecase

import ru.webarmour.weatercompose.domain.repository.FavouriteRepository
import ru.webarmour.weatercompose.domain.repository.SearchRepository
import javax.inject.Inject

class SearchCityUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(query: String) = searchRepository.search(query)
}