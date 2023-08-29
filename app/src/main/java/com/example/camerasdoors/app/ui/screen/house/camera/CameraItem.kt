package com.example.camerasdoors.app.ui.screen.house.camera

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.camerasdoors.R
import com.example.camerasdoors.app.ui.theme.CirceRegular
import com.example.camerasdoors.app.ui.theme.Grey80
import com.example.camerasdoors.app.ui.theme.Grey90
import com.example.camerasdoors.domain.model.Camera
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun CameraItem(camera: Camera) {
    var isShifted by remember { mutableStateOf(value = false) }
    val targetValue = if (isShifted) (-45).dp else 0.dp
    val offset = animateDpAsState(
        targetValue = targetValue,
        label = ""
    ).value

    Box {
        Box(
            modifier = Modifier
                .padding(end = 21.dp)
                .align(alignment = Alignment.CenterEnd)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_bg_circle),
                contentDescription = stringResource(id = R.string.background_circle)
            )
            Image(
                painter = painterResource(id = if (camera.favorites) R.drawable.ic_staron else R.drawable.ic_staroff),
                contentDescription = stringResource(id = R.string.stars_on_off),
                modifier = Modifier.align(alignment = Alignment.Center)
            )
        }
        Column(
            modifier = Modifier
                .offset(x = offset)
                .clickable {
                    isShifted = !isShifted
                }
                .padding(
                    horizontal = 21.dp,
                    vertical = 12.dp
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .border(
                    border = BorderStroke(
                        width = 0.1.dp,
                        color = Grey90
                    ),
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .fillMaxWidth()
        ) {
            Box {
                AsyncImage(
                    model = camera.snapshot,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(
                            shape = RoundedCornerShape(
                                topStart = 12.dp,
                                topEnd = 12.dp,
                                bottomEnd = 0.dp,
                                bottomStart = 0.dp
                            )
                        ),
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_play_button),
                    contentDescription = stringResource(id = R.string.play_button),
                    modifier = Modifier.align(alignment = Alignment.Center)
                )
                if (camera.favorites)
                    Image(
                        painter = painterResource(id = R.drawable.ic_staron),
                        contentDescription = stringResource(id = R.string.star_on),
                        modifier = Modifier
                            .align(alignment = Alignment.TopEnd)
                            .padding(all = 5.dp)
                    )
                if (camera.rec)
                    Image(
                        painter = painterResource(id = R.drawable.ic_rec),
                        contentDescription = stringResource(R.string.rec_on),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .padding(all = 8.dp)
                    )
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = camera.name,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 22.dp,
                        bottom = 20.dp
                    ),
                    color = Grey80,
                    fontSize = 17.sp,
                    fontWeight = FontWeight(weight = 400),
                    fontFamily = CirceRegular,
                    lineHeight = 25.06.sp
                )
            }
        }
    }
}