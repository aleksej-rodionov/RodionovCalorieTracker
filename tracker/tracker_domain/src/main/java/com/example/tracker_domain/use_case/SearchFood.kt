package com.example.tracker_domain.use_case

import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.repo.TrackerRepo

class SearchFood(
    private val trackerRepo: TrackerRepo
) {

    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 40
    ): Result<List<TrackableFood>> {

        if (query.isBlank()) {
            return Result.success(emptyList())
        }

        return trackerRepo.searchFood(
            query.trim(),
            page,
            pageSize
        )
    }
}