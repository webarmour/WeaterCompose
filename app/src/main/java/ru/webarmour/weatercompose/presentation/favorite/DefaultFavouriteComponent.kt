package ru.webarmour.weatercompose.presentation.favorite

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.webarmour.weatercompose.domain.entity.City
import ru.webarmour.weatercompose.presentation.extensions.componentScope
import javax.inject.Inject

class DefaultFavouriteComponent @Inject constructor(
    private val favouriteStoreFactory: FavouriteStoreFactory,
    private val onCityItemClick:(City)->Unit,
    private val onAddFavouriteClick: ()->Unit,
    private val onSearchClick:()->Unit,
    componentContext: ComponentContext,
) : FavouriteComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { favouriteStoreFactory.create() }

    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when(it){
                    is FavouriteStore.Label.CityItemClick -> {
                        onCityItemClick(it.city)
                    }
                    FavouriteStore.Label.ClickSearch -> {
                        onSearchClick()
                    }
                    FavouriteStore.Label.ClickToAddFavourite -> {
                        onAddFavouriteClick()
                    }
                }
            }
        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<FavouriteStore.State> = store.stateFlow

    override fun onClickSearch() {
        store.accept(FavouriteStore.Intent.ClickSearch)
    }

    override fun onClickAddFavourite() {
        store.accept(FavouriteStore.Intent.ClickToAddFavourite)
    }

    override fun onClickCityItem(city: City) {
        store.accept(FavouriteStore.Intent.CityItemClick(city))
    }
}