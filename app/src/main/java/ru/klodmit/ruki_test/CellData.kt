package ru.klodmit.ruki_test


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

data class CellData(
    val color: Color,
    val title: String,
    val subtitle: String,
    val image: Painter
)