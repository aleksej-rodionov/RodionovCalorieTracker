package com.example.tracker_domain.di

import com.example.core.domain.preferences.Preferences
import com.example.tracker_domain.repo.TrackerRepo
import com.example.tracker_domain.use_case.*
import dagger.Module
import dagger.Provides

@Module
class TrackerDomainModule {

//    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        repo: TrackerRepo,
        preferences: Preferences
    ): TrackerUseCases {
        return TrackerUseCases(
            trackFood = TrackFood(repo),
            searchFood = SearchFood(repo),
            getFoodsForDate = GetFoodsForDate(repo),
            deleteTrackedFood = DeleteTrackedFood(repo),
            calculateMealNutrients = CalculateMealNutrients(preferences)
        )
    }
}