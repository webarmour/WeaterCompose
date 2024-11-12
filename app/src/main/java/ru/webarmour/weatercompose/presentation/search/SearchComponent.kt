package ru.webarmour.weatercompose.presentation.search

import kotlinx.coroutines.flow.StateFlow
import ru.webarmour.weatercompose.domain.entity.City

interface SearchComponent {


    val mode: StateFlow<SearchStore.State>

    fun changeSearchQuery(query:String)

    fun onClickBack()

    fun onClickSearch()

    fun onCityClicked(city: City)

}