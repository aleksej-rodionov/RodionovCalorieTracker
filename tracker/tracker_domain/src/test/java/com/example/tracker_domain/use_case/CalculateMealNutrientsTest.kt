package com.example.tracker_domain.use_case

import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo
import com.example.core.domain.preferences.Preferences
import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackedFood
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import kotlin.random.Random

class CalculateMealNutrientsTest {

    private lateinit var calculateMealNutrients: CalculateMealNutrients

    @Before
    fun setUp() {
        val prefs = mockk<Preferences>(relaxed = true)
        every { prefs.loadUserInfo() } returns UserInfo(
            gender = Gender.Male,
            age = 32,
            weight = 65f,
            height = 177,
            activityLevel = ActivityLevel.Medium,
            goalType = GoalType.KeepWeight,
            carbRatio = 0.4f,
            proteinRatio = 0.3f,
            fatRatio = 0.3f
        )
        calculateMealNutrients = CalculateMealNutrients(prefs)
    }

    @Test
    fun `Calories for breakfast properly calculated`() {

        val trackedFoods = (1..30).map {
            TrackedFood(
                "name",
                Random.nextInt(100),
                Random.nextInt(100),
                Random.nextInt(100),
                null,
                MealType.fromString(
                    listOf("breakfast", "lunch", "dinner", "snack").random()
                ),
                100,
                LocalDate.now(),
                Random.nextInt(2000)
            )
        }

        val result = calculateMealNutrients.invoke(trackedFoods)

        val breakfastCalories = result.mealNutrients.values
            .filter { it.mealType == MealType.Breakfast }
            .sumOf { it.calories }

        val expectedBreakfastCalories = trackedFoods
            .filter { it.mealType == MealType.Breakfast }
            .sumOf { it.calories }

        assertThat(breakfastCalories).isEqualTo(expectedBreakfastCalories)
    }
}