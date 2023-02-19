package com.example.onboarding_presentation.goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.preferences.Preferences
import com.example.core.navigation.Route
import com.example.core.util.UiEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
    private val prefs: Preferences
): ViewModel() {

    var selectedGoalState by mutableStateOf<GoalType>(GoalType.KeepWeight)
        private set

    private val _uiEffect = Channel<UiEffect>()
    val uiEffect = _uiEffect.receiveAsFlow()

    fun onGoalTypeSelect(goalType: GoalType) {
        selectedGoalState = goalType
    }

    fun onNextClick() {
        viewModelScope.launch {
            prefs.saveGoalType(selectedGoalState)
            _uiEffect.send(UiEffect.Navigate(Route.NUTRIENT_GOAL))
        }
    }
}