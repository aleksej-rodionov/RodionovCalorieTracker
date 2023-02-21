package com.example.tracker_domain.use_case

import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repo.TrackerRepo

class DeleteTrackedFood(
    private val repo: TrackerRepo
) {

    suspend operator fun invoke(trackedFood: TrackedFood) {
        repo.deleteTrackedFood(trackedFood)
    }
}