package com.example.easy_transport.repository


import android.content.Context

class OnBoardingRepository(private val context: Context) {

    fun getOnBoardingCompleted(): Boolean {
        val preferences = context.getSharedPreferences("onBoardingPrefs", Context.MODE_PRIVATE)
        return preferences.getBoolean("onBoardingCompleted", false)
    }

    fun setOnBoardingCompleted() {
        val preferences = context.getSharedPreferences("onBoardingPrefs", Context.MODE_PRIVATE)
        preferences.edit().putBoolean("onBoardingCompleted", true).apply()
    }
}
