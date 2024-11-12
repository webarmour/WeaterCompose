package ru.webarmour.weatercompose.presentation.favorite

import kotlinx.coroutines.flow.StateFlow
import ru.webarmour.weatercompose.domain.entity.City

interface FavouriteComponent {

    val model: StateFlow<FavouriteStore.State>

    fun onClickSearch()

    fun onClickAddFavourite()

    fun onClickCityItem(city: City)

}