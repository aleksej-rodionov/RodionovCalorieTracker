package com.example.tracker_data.repo

import com.example.tracker_data.local.TrackerDao
import com.example.tracker_data.mapper.toTrackableFood
import com.example.tracker_data.mapper.toTrackedFood
import com.example.tracker_data.mapper.toTrackedFoodEntity
import com.example.tracker_data.remote.OpenFoodApi
import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repo.TrackerRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.Exception
import java.time.LocalDate

class TrackerRepoImpl(
    private val trackerDao: TrackerDao,
    private val api: OpenFoodApi
) : TrackerRepo {

    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = api.searchFood(query, page, pageSize)
            Result.success(
                searchDto.products
                    .filter {
                        val calculatedCalories =
                            it.nutriments.carbohydrates100g * 4f +
                                    it.nutriments.proteins100g * 4f +
                                    it.nutriments.fat100g * 9f
                        val lowerBound = calculatedCalories * 0.99f
                        val upperBound = calculatedCalories * 1.01f
                        it.nutriments.energyKcal100g in (lowerBound..upperBound)
                    }
                    .mapNotNull {
                        it.toTrackableFood()
                    }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(trackedFood: TrackedFood) {
        trackerDao.insertTrackedFood(trackedFood.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(trackedFood: TrackedFood) {
        trackerDao.deleteTrackedFood(trackedFood.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return trackerDao.getFoodsForDate(
            localDate.dayOfMonth,
            localDate.monthValue,
            localDate.year
        ).map {
            it.map { entity ->
                entity.toTrackedFood()
            }
        }
    }
}