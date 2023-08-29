package com.example.camerasdoors.app.ui.screen.house.camera

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.camerasdoors.R
import com.example.camerasdoors.app.ui.theme.CirceRegular
import com.example.camerasdoors.app.ui.theme.Grey70
import com.example.camerasdoors.domain.model.Camera

@Composable
fun CamerasScreen(cameras: List<Camera>?) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Text(
                text = stringResource(id = R.string.living_room),
                modifier = Modifier.padding(
                    start = 21.dp,
                    top = 16.dp
                ),
                color = Grey70,
                fontWeight = FontWeight(weight = 300),
                fontSize = 21.sp,
                fontFamily = CirceRegular,
                textAlign = TextAlign.Start,
                lineHeight = 30.95.sp
            )
        }
        cameras?.let { cameras ->
            items(count = cameras.count()) { index ->
                CameraItem(camera = cameras[index])
            }
        }
    }
}