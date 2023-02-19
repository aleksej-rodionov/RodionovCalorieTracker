package com.example.core.util

sealed class UiEffect {
    data class Navigate(val route: String): UiEffect()
    object NavigateUp: UiEffect()
    data class ShowSnackbar(val msg: UiText): UiEffect()
}
