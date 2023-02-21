package com.example.tracker_domain.use_case

import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repo.TrackerRepo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodsForDate(
    private val repo: TrackerRepo
) {

    operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> {
        return repo.getFoodsForDate(date)
    }
}