package com.example.onboarding_presentation.gender

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.Gender
import com.example.core.domain.preferences.Preferences
import com.example.core.util.UiEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
    private val prefs: Preferences
): ViewModel() {

    var selectedGenderState by mutableStateOf<Gender>(Gender.Male)
        private set

    private val _uiEffect = Channel<UiEffect>()
    val uiEffect = _uiEffect.receiveAsFlow()

    fun onGenderClick(gender: Gender) {
        selectedGenderState = gender
    }

    fun onNextClick() {
        viewModelScope.launch {
            prefs.saveGender(selectedGenderState)
            _uiEffect.send(UiEffect.NavigateSuccess)
        }
    }
}