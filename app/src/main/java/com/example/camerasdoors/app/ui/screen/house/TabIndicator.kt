package com.example.camerasdoors.app.ui.screen.house

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.TabPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.camerasdoors.app.ui.theme.Blue

@Composable
fun TabIndicator(
    tabPosition: List<TabPosition>,
    index: Int
) {
    val width = tabPosition[index].width
    val offsetX = tabPosition[index].left
    Box(
        modifier = Modifier
            .wrapContentSize(align = Alignment.BottomStart)
            .width(width = width)
            .height(height = 2.dp)
            .offset(x = offsetX)
            .background(color = Blue)
    )
}