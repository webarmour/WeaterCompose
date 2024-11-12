package ru.webarmour.weatercompose.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.webarmour.weatercompose.presentation.details.DetailsComponent
import ru.webarmour.weatercompose.presentation.favorite.FavouriteComponent
import ru.webarmour.weatercompose.presentation.search.SearchComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {

        data class Favourite(val component: FavouriteComponent) : Child

        data class Search(val component: SearchComponent) : Child

        data class Details(val component: DetailsComponent) : Child
    }
}