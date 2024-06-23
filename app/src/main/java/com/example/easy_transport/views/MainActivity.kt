package com.example.easy_transport.views


import com.example.easy_transport.views.onBoarding.OnBoardingScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.easy_transport.ui.theme.OnboardingScreeenTheme
import com.example.easy_transport.views.auth.AuthScreen

import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "onBoarding") {
                composable("onBoarding") { OnBoardingScreen(navController) }
                composable("auth") { AuthScreen() }
            }
        }
    }
}
@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OnboardingScreeenTheme {
        Surface(color = MaterialTheme.colors.background) {
            //OnBoarding()
        }
    }
}