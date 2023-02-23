package com.example.rodionovcalorietracker.repo

import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repo.TrackerRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.time.LocalDate
import kotlin.random.Random

class TrackerRepoFake: TrackerRepo {

    var shouldReturnError = false

    private val trackedFoodList = mutableListOf<TrackedFood>()
    var searchResults = listOf<TrackableFood>()

    private val getFoodsForDateFlow = MutableSharedFlow<List<TrackedFood>>(replay = 1)
    // replay = 1 means it will emit 1 last value to the new collectors in case they're slow

    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return if (shouldReturnError) {
            Result.failure(Throwable())
        } else {
            Result.success(searchResults)
        }
    }

    override suspend fun insertTrackedFood(trackedFood: TrackedFood) {
        trackedFoodList.add(trackedFood.copy(id = Random.nextInt()))
        getFoodsForDateFlow.emit(trackedFoodList)
    }

    override suspend fun deleteTrackedFood(trackedFood: TrackedFood) {
        trackedFoodList.remove(trackedFood)
        getFoodsForDateFlow.emit(trackedFoodList)
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return getFoodsForDateFlow
    }
}