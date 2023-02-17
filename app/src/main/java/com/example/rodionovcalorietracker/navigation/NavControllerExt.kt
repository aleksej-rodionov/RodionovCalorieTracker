package com.example.rodionovcalorietracker.navigation

import androidx.navigation.NavController
import com.example.core.util.UiEffect

fun NavController.navigate(effect: UiEffect.Navigate) {
    this.navigate(effect.route)
}