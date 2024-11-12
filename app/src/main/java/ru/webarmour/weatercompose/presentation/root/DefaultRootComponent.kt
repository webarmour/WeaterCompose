package ru.webarmour.weatercompose.presentation.root

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
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

    private val navigationStack = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigationStack,
        initialConfiguration = Config.Favourite,
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(
        config: Config,
        componentContext: ComponentContext,
    ): RootComponent.Child {
        return when (config) {
            is Config.Detail -> {
                val component = detailsComponent.create(
                    city = config.city,
                    onBackClicked = {
                        navigationStack.pop()
                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Details(component)
            }

            Config.Favourite -> {
                val component = favouriteComponent.create(
                    onAddFavouriteClick = {
                        navigationStack.push(Config.Search(openReason = OpenReason.AddToFavourite))
                    },
                    onSearchClick = {
                        navigationStack.push(Config.Search(openReason = OpenReason.RegularSearch))
                    },
                    onCityItemClick = {
                        navigationStack.push(Config.Detail(city = it))
                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Favourite(component)
            }

            is Config.Search -> {
                val component = searchComponent.create(
                    onOpenForecastClicked = {
                        navigationStack.push(Config.Detail(city = it))
                    },
                    onBackClicked = {
                        navigationStack.pop()
                    },
                    onSavedToFavouriteClicked = {
                        navigationStack.pop()
                    },
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