package com.example.tracker_domain.model

data class TrackableFood(
    val name: String,
    val imageUrl: String?,
    val caloriesPer100g: Int,
    val carbsPer100g: Int,
    val proteinPer1000g: Int,
    val fatPer1000g: Int
)
