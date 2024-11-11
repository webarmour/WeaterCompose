package ru.webarmour.weatercompose.domain.usecase

import ru.webarmour.weatercompose.domain.repository.FavouriteRepository
import javax.inject.Inject

class ObserveFavouriteStateUseCase @Inject constructor(
    private val favouriteRepository: FavouriteRepository
) {

    operator fun invoke(cityId:Int) = favouriteRepository.observeIsFavourite(cityId)
}