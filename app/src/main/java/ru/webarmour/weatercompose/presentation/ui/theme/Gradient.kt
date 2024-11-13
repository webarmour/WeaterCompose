package ru.webarmour.weatercompose.presentation.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class Gradient(
    val primaryGradient: Brush,
    val secondaryGradient: Brush,
    val shadowColor: Color,
) {

    constructor(
        firstColor: Color,
        secondColor: Color,
        thirdColor: Color,
        fourthColor: Color,
    ) : this(
        primaryGradient = Brush.linearGradient(listOf(firstColor, secondColor)),
        secondaryGradient = Brush.linearGradient(listOf(thirdColor, fourthColor)),
        shadowColor = firstColor
    )
}

object CardGradients{
    val gradients = listOf(
        Gradient(
            firstColor = Color(0xFFFFDF37),
            secondColor = Color(0xFFFF5621),
            thirdColor = Color(0xFFFFCE21),
            fourthColor = Color(0xFFFF7F57)
        ),
        Gradient(
            firstColor = Color(0xFFFB37FF),
            secondColor = Color(0xFF3531FF),
            thirdColor = Color(0xA85000FF),
            fourthColor = Color(0x8A8433FF)
        ),
        Gradient(
            firstColor = Color(0xD3AA1336),
            secondColor = Color(0xD3501724),
            thirdColor = Color(0xD3971A69),
            fourthColor = Color(0xE8660931)
        ),
        Gradient(
            firstColor = Color(0xFF53D530),
            secondColor = Color(0xFF076632),
            thirdColor = Color(0xFF27FC86),
            fourthColor = Color(0xFF156328)
        ),
    )
}