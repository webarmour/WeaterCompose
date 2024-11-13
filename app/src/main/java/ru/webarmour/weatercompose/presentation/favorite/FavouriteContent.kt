package ru.webarmour.weatercompose.presentation.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.webarmour.weatercompose.presentation.ui.theme.CardGradients
import ru.webarmour.weatercompose.presentation.ui.theme.Gradient

@Composable
fun FavouriteContent(component: FavouriteComponent) {

    val state by component.model.collectAsState()

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(
            items = state.cityItems,
            key = { _, item -> item.city.id }
        ) { index, item ->
            CityCard(
                cityItem = item,
                index
            )

        }
    }
}

@Composable
private fun CityCard(
    cityItem: FavouriteStore.State.CityItem,
    index: Int,
) {
    val gradients = getGradientByIndex(index)
    Card(
        modifier = Modifier
            .fillMaxSize()
            .shadow(
                elevation = 16.dp,
                spotColor = gradients.shadowColor,
                shape = MaterialTheme.shapes.extraLarge
            ),
        colors = CardDefaults.cardColors(containerColor = Color.Blue),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradients.primaryGradient)
                .fillMaxSize()
                .sizeIn(minHeight = 196.dp)
                .drawBehind {
                    drawCircle(
                        brush = gradients.secondaryGradient,
                        center = Offset(
                            x = center.x - size.width / 5,
                            y = center.y + size.height / 2
                        ),
                        radius = size.maxDimension / 2
                    )
                }
                .padding(24.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.BottomStart),
                text = cityItem.city.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.background
            )
        }

    }
}


private fun getGradientByIndex(index: Int): Gradient {
    val gradients = CardGradients.gradients
    return gradients[index % gradients.size]
}











