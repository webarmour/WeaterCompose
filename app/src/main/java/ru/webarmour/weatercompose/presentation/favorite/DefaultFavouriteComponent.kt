package ru.webarmour.weatercompose.presentation.favorite

import com.arkivanov.decompose.ComponentContext

class DefaultFavouriteComponent(
    componentContext: ComponentContext
) : FavouriteComponent, ComponentContext by componentContext