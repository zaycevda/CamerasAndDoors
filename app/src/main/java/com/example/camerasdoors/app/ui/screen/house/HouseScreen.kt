package com.example.camerasdoors.app.ui.screen.house

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.camerasdoors.R
import com.example.camerasdoors.app.ui.screen.house.camera.CamerasScreen
import com.example.camerasdoors.app.ui.screen.house.door.DoorsScreen
import com.example.camerasdoors.app.ui.theme.CirceRegular
import com.example.camerasdoors.app.ui.theme.Grey100
import com.example.camerasdoors.app.ui.theme.Grey70
import com.example.camerasdoors.app.viewmodel.HouseViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun HouseScreen() {
    val viewModel = hiltViewModel<HouseViewModel>()
    val refreshing by viewModel.isRefreshing.observeAsState()
    val cameras by viewModel.cameras.observeAsState()
    val doors by viewModel.doors.observeAsState()

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshing ?: false,
        onRefresh = { viewModel.refresh() }
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.getCameras()
        viewModel.getDoors()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Grey100)
            .pullRefresh(state = pullRefreshState)
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp),
                color = Grey70,
                fontSize = 21.sp,
                fontWeight = FontWeight(weight = 400),
                fontFamily = CirceRegular,
                textAlign = TextAlign.Center,
                lineHeight = 26.sp
            )
            TabHouse(
                selectedTabIndex = pagerState.currentPage,
                onSelectedTab = { tabPage ->
                    scope.launch {
                        pagerState.animateScrollToPage(page = tabPage.ordinal)
                    }
                }
            )
            HorizontalPager(
                state = pagerState,
                count = TabPage.values().count()
            ) { index ->
                when (index) {
                    0 -> CamerasScreen(cameras = cameras)
                    1 -> DoorsScreen(doors = doors)
                }
            }
        }
        PullRefreshIndicator(
            refreshing = refreshing ?: true,
            state = pullRefreshState,
            modifier = Modifier.align(alignment = Alignment.TopCenter)
        )
    }
}

@Preview
@Composable
fun HouseScreenPreview() {
    HouseScreen()
}