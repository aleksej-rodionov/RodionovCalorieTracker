package com.example.onboarding_presentation.nutrient_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.preferences.Preferences
import com.example.core.domain.use_case.FilterOutDigits
import com.example.core.util.UiEffect
import com.example.onboarding_domain.use_case.ValidateNutrients
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits,
    private val validateNutrients: ValidateNutrients
) : ViewModel() {

    var state by mutableStateOf(NutrientGoalState())

    private val _uiEffect = Channel<UiEffect>()
    val uiEffect = _uiEffect.receiveAsFlow()

    fun onEvent(event: NutrientGoalEvent) {
        when (event) {
            is NutrientGoalEvent.OnCarbRatioEnter -> {
                state = state.copy(carbsRatio = filterOutDigits(event.ratio))
            }
            is NutrientGoalEvent.OnProteinRatioEnter -> {
                state = state.copy(proteinRatio = filterOutDigits(event.ratio))
            }
            is NutrientGoalEvent.OnFatRatioEnter -> {
                state = state.copy(fatRatio = filterOutDigits(event.ratio))
            }
            is NutrientGoalEvent.OnNextClick -> {
                val result = validateNutrients(state.carbsRatio, state.proteinRatio, state.fatRatio)

                when (result) {
                    is ValidateNutrients.Result.Success -> {
                        preferences.saveCarbRatio(result.carbsRatio)
                        preferences.saveProteinRatio(result.proteinRatio)
                        preferences.saveFatRatio(result.fatRatio)
                        viewModelScope.launch {
                            _uiEffect.send(UiEffect.NavigateSuccess)
                        }
                    }
                    is ValidateNutrients.Result.Error -> {
                        viewModelScope.launch {
                            _uiEffect.send(UiEffect.ShowSnackbar(result.msg))
                        }
                    }
                }
            }
        }
    }
}

data class NutrientGoalState(
    val carbsRatio: String = "40",
    val proteinRatio: String = "30",
    val fatRatio: String = "30"
)

sealed class NutrientGoalEvent {
    data class OnCarbRatioEnter(val ratio: String) : NutrientGoalEvent()
    data class OnProteinRatioEnter(val ratio: String) : NutrientGoalEvent()
    data class OnFatRatioEnter(val ratio: String) : NutrientGoalEvent()
    object OnNextClick : NutrientGoalEvent()
}