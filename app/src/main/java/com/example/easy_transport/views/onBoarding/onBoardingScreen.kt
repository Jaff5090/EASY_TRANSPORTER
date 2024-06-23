package com.example.easy_transport.views.onBoarding

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.easy_transport.R
import com.example.easy_transport.model.OnBoardingItem
import com.example.easy_transport.viewmodel.OnBoardingViewModel
import com.example.easy_transport.viewmodel.OnBoardingViewModelFactory
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun OnBoardingScreen(navController: NavController = rememberNavController()) {
    val context = LocalContext.current
    val viewModel: OnBoardingViewModel = viewModel(factory = OnBoardingViewModelFactory(context))
    val isOnBoardingCompleted by viewModel.isOnBoardingCompleted.collectAsState()

    if (isOnBoardingCompleted) {
        LaunchedEffect(Unit) {
            navController.navigate("auth") {
                popUpTo("onBoarding") { inclusive = true }
            }
        }
    } else {
        val scope = rememberCoroutineScope()
        Column(Modifier.fillMaxSize()) {
            val items = OnBoardingItem.get()
            val statePager = rememberPagerState()
            TopSection(navController, viewModel)
            HorizontalPager(
                state = statePager,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.8f),
                count = items.size
            ) { page ->
                OnBoardingItem(items[page], page)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                HorizontalPagerIndicator(
                    pagerState = statePager,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterVertically)
                )

                BottomSection(size = items.size, index = statePager.currentPage, viewModel,navController) {
                    if (statePager.currentPage + 1 < items.size) {
                        scope.launch {
                            statePager.animateScrollToPage(statePager.currentPage + 1)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopSection(navController: NavController, viewModel: OnBoardingViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        TextButton(
            onClick = {
                viewModel.setOnBoardingCompleted()
                navController.navigate("auth") {
                    popUpTo("onBoarding") { inclusive = true }
                }
            },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Text(text = "Passer", color = MaterialTheme.colors.onBackground)
        }
    }
}



@Composable
fun BottomSection(
    size: Int,
    index: Int,
    viewModel: OnBoardingViewModel,
    navController: NavController,
    onNextClicked: () -> Unit
) {
    val animatedSize by animateDpAsState(targetValue = if (index == size - 1) 72.dp else 56.dp,
        label = ""
    )

    Box(
        modifier = Modifier
            .padding(12.dp)
            .size(animatedSize)
            .clip(CircleShape)
            .border(
                width = 2.dp,
                color = colorResource(id = R.color.teal_200),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        FloatingActionButton(
            onClick = {
                if (size == index + 1) {
                    viewModel.setOnBoardingCompleted()
                    navController.navigate("auth") {
                        popUpTo("onBoarding") { inclusive = true }
                    }
                } else {
                    onNextClicked()
                }
            },
            backgroundColor = colorResource(id = R.color.teal_200)
        ) {
            if (size == index + 1) {
                Text(
                    text = "GO",
                    fontSize = 10.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            } else {
                Icon(Icons.Outlined.ArrowForward, contentDescription = null, tint = Color.Black)
            }
        }
    }
}


@Composable
fun OnBoardingItem(item: OnBoardingItem, index: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (index == 1) {

            Image(
                painter = painterResource(id = item.Image),
                contentDescription = "Screen1",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(300.dp)
                    .clip(CircleShape)
                    .border(2.dp, colorResource(id = R.color.teal_200), CircleShape)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                Text(
                    text = stringResource(id = item.title),
                    fontSize = 28.sp,
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Text(
                    text = stringResource(id = item.text),
                    fontSize = 15.sp,
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                Text(
                    text = stringResource(id = item.title),
                    fontSize = 28.sp,
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Text(
                    text = stringResource(id = item.text),
                    fontSize = 15.sp,
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }

            Image(
                painter = painterResource(id = item.Image),
                contentDescription = "Screen1",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(300.dp)
                    .clip(CircleShape)
                    .border(2.dp, colorResource(id = R.color.teal_200), CircleShape)
            )
        }
    }
}
