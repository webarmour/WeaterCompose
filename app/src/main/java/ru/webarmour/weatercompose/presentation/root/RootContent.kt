package ru.webarmour.weatercompose.presentation.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import ru.webarmour.weatercompose.presentation.details.DetailsContent
import ru.webarmour.weatercompose.presentation.favorite.FavouriteContent
import ru.webarmour.weatercompose.presentation.search.SearchContent
import ru.webarmour.weatercompose.presentation.ui.theme.WeaterComposeTheme

@Composable
fun RootContent(component: RootComponent) {


    WeaterComposeTheme {
        Children(stack = component.stack) {

            when (val instance = it.instance) {
                is RootComponent.Child.Details -> {
                    DetailsContent(component = instance.component)
                }

                is RootComponent.Child.Favourite -> {
                    FavouriteContent(component = instance.component)
                }

                is RootComponent.Child.Search -> {
                    SearchContent(component = instance.component)
                }
            }

        }
    }


}