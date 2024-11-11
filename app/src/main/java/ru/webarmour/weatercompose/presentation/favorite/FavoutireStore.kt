package ru.webarmour.weatercompose.presentation.favorite

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import ru.webarmour.weatercompose.presentation.favorite.FavoutireStore.Intent
import ru.webarmour.weatercompose.presentation.favorite.FavoutireStore.Label
import ru.webarmour.weatercompose.presentation.favorite.FavoutireStore.State

internal interface FavoutireStore : Store<Intent, State, Label> {

    sealed interface Intent {
    }

    data class State(val todo:Unit)

    sealed interface Label {
    }
}

internal class FavoutireStoreFactory(
    private val storeFactory: StoreFactory
) {

    fun create(): FavoutireStore =
        object : FavoutireStore, Store<Intent, State, Label> by storeFactory.create(
            name = "FavoutireStore",
            initialState = State(Unit),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
    }

    private sealed interface Msg {
    }

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
        }
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
        }

        override fun executeAction(action: Action, getState: () -> State) {
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(message: Msg): State = State(Unit)
    }
}
