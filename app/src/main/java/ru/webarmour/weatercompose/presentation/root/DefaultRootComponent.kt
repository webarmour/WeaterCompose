package ru.webarmour.weatercompose.presentation.root

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.parcelize.Parcelize
import ru.webarmour.weatercompose.domain.entity.City
import ru.webarmour.weatercompose.presentation.details.DefaultDetailsComponent
import ru.webarmour.weatercompose.presentation.favorite.DefaultFavouriteComponent
import ru.webarmour.weatercompose.presentation.search.DefaultSearchComponent
import ru.webarmour.weatercompose.presentation.search.OpenReason

class DefaultRootComponent @AssistedInject constructor(
    private val detailsComponent: DefaultDetailsComponent.Factory,
    private val favouriteComponent: DefaultFavouriteComponent.Factory,
    private val searchComponent: DefaultSearchComponent.Factory,
    @Assisted("componentContext") componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    override val stack: Value<ChildStack<*, RootComponent.Child>>
        get() = TODO("Not yet implemented")

    private fun child(
        config: Config,
        componentContext: ComponentContext,
    ): RootComponent.Child {
        return when (config) {
            is Config.Detail -> {
                val component = detailsComponent.create(
                    city = config.city,
                    onBackClicked = {

                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Details(component)
            }

            Config.Favourite -> {
                val component = favouriteComponent.create(
                    onAddFavouriteClick = {

                    },
                    onSearchClick = {

                    },
                    onCityItemClick = {

                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Favourite(component)
            }

            is Config.Search -> {
                val component = searchComponent.create(
                    onOpenForecastClicked = {},
                    onBackClicked = {},
                    onSavedToFavouriteClicked = {},
                    openReason = config.openReason,
                    componentContext = componentContext
                )
                RootComponent.Child.Search(component)
            }
        }
    }

    sealed interface Config : Parcelable {

        @Parcelize
        data object Favourite : Config

        @Parcelize
        data class Search(val openReason: OpenReason) : Config

        @Parcelize
        data class Detail(val city: City) : Config
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext,
        ): DefaultRootComponent
    }
}