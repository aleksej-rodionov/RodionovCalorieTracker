package com.example.tracker_domain.use_case

import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repo.TrackerRepo
import java.time.LocalDate
import kotlin.math.roundToInt

class TrackFood(
    private val trackerRepo: TrackerRepo
) {

    suspend operator fun invoke(
        food: TrackableFood,
        amount: Int,
        mealType: MealType,
        date: LocalDate
    ) {

        trackerRepo.insertTrackedFood(
            trackedFood = TrackedFood(
                food.name,
                carbs = ((food.carbsPer100g / 100f) * amount).roundToInt(),
                protein = ((food.proteinPer1000g / 100f) * amount).roundToInt(),
                fat = ((food.fatPer1000g / 100f) * amount).roundToInt(),
                calories = ((food.caloriesPer100g / 100f) * amount).roundToInt(),
                imageUrl = food.imageUrl,
                mealType = mealType,
                amount = amount,
                date = date
            ) // and id is generated automatically by Room
        )
    }
}