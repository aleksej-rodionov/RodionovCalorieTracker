package com.example.core.util

sealed class UiEffect {
    object NavigateSuccess: UiEffect()
    object NavigateUp: UiEffect()
    data class ShowSnackbar(val msg: UiText): UiEffect()
}
