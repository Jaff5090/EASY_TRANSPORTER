package com.example.easy_transport


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.easy_transport.onBoarding.OnBoarding
import com.example.easy_transport.ui.theme.OnboardingScreeenTheme

import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnboardingScreeenTheme {
                Surface(color = MaterialTheme.colors.background) {
                    OnBoarding()
                }
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
            OnBoarding()
        }
    }
}