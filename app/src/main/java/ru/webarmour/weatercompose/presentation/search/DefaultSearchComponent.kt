package ru.webarmour.weatercompose.presentation.search

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.webarmour.weatercompose.domain.entity.City
import ru.webarmour.weatercompose.presentation.details.DetailsStore
import ru.webarmour.weatercompose.presentation.extensions.componentScope
import javax.inject.Inject

class DefaultSearchComponent @Inject constructor(
    private val onBackClicked:()->Unit,
    private val onOpenForecastClicked:(City)->Unit,
    private val onSavedToFavouriteClicked:()->Unit,
    private val openReason: OpenReason,
    private val searchFactory: SearchStoreFactory,
    componentContext: ComponentContext
) : SearchComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { searchFactory.create(openReason) }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect{
                when(it){
                    SearchStore.Label.ClickBack -> {
                        onBackClicked()
                    }
                    is SearchStore.Label.OpenForecast -> {
                        onOpenForecastClicked(it.city)
                    }
                    SearchStore.Label.SavedToFavourite -> {
                        onSavedToFavouriteClicked()
                    }
                }
            }
        }
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    override val mode: StateFlow<SearchStore.State> = store.stateFlow

    override fun changeSearchQuery(query:String) {
        store.accept(SearchStore.Intent.ChangeSearchQuery(query))
    }

    override fun onClickBack() {
        store.accept(SearchStore.Intent.ClickBack)
    }

    override fun onClickSearch() {
        store.accept(SearchStore.Intent.ClickSearch)
    }

    override fun onCityClicked(city: City) {
        store.accept(SearchStore.Intent.ClickCity(city))
    }
}