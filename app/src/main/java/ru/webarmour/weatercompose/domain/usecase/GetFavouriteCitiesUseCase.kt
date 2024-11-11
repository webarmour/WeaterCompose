package ru.webarmour.weatercompose.domain.usecase

import ru.webarmour.weatercompose.domain.repository.FavouriteRepository
import javax.inject.Inject

class GetFavouriteCitiesUseCase @Inject constructor(
    private val favouriteRepository: FavouriteRepository
) {

    operator fun invoke() = favouriteRepository.favouriteCities
}