package com.example.camerasdoors.app.ui.screen.house.door

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.camerasdoors.domain.model.Door

@Composable
fun DoorsScreen(doors: List<Door>?) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        doors?.let { doors ->
            items(count = doors.count()) { index ->
                DoorItem(door = doors[index])
            }
        }
    }
}