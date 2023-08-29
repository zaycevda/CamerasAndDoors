package com.example.camerasdoors.app.ui.screen.house

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.camerasdoors.R
import com.example.camerasdoors.app.ui.theme.CirceRegular
import com.example.camerasdoors.app.ui.theme.Grey100
import com.example.camerasdoors.app.ui.theme.Grey70

@Composable
fun TabHouse(
    selectedTabIndex: Int,
    onSelectedTab: (TabPage) -> Unit
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier.padding(top = 20.dp),
        indicator = { tabPosition ->
            TabIndicator(
                tabPosition = tabPosition,
                index = selectedTabIndex
            )
        }
    ) {
        TabPage.values().forEachIndexed { index, tabPage ->
            Tab(
                selected = index == selectedTabIndex,
                onClick = { onSelectedTab(tabPage) },
                modifier = Modifier.background(color = Grey100),
                text = {
                    Text(
                        text = tabPage.title,
                        color = Grey70,
                        fontSize = 17.sp,
                        fontWeight = FontWeight(weight = 400),
                        fontFamily = CirceRegular,
                        lineHeight = 16.sp
                    )
                }
            )
        }
    }
}