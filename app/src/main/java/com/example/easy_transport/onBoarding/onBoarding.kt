package com.example.easy_transport.onBoarding


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easy_transport.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@Composable
@Preview
fun TopSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {

        IconButton(
            onClick = { },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(Icons.Outlined.KeyboardArrowLeft, null)
        }

        TextButton(
            onClick = { /* Main Screen */ },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Text(text = "Passer", color = MaterialTheme.colors.onBackground)
        }
    }
}

@ExperimentalPagerApi
@Composable
@Preview
fun OnBoarding() {
    val scope = rememberCoroutineScope()
    Column(Modifier.fillMaxSize()) {
        val items = OnBoardingItem.get()
        val statePager = rememberPagerState()
        TopSection()
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

            BottomSection(size = items.size, index = statePager.currentPage) {
                if (statePager.currentPage + 1 < items.size) {

                    scope.launch {
                        statePager.animateScrollToPage(statePager.currentPage + 1)
                    }
                }
            }
        }
    }
}


@ExperimentalPagerApi
@Composable

fun BottomSection(
    size: Int,
    index: Int,
    onNextClicked: () -> Unit
) {
    Box(
        modifier = Modifier.padding(12.dp)
    ) {
        val buttontext = if (size == index + 1) "Démarrer" else "Suivant"
        FloatingActionButton(
            onClick = onNextClicked,
            modifier = Modifier.align(Alignment.CenterEnd),
            backgroundColor = colorResource(id = R.color.teal_200)
        ) {
            Text(
                text = buttontext,
                fontSize = 10.sp,
                color = Color.Black
            )
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

