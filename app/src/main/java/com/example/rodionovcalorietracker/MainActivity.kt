package com.example.rodionovcalorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.onboarding_presentation.welcome.WelcomeScreen
import com.example.rodionovcalorietracker.ui.theme.RodionovCalorieTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RodionovCalorieTrackerTheme {
                WelcomeScreen()
            }
        }
    }
}
