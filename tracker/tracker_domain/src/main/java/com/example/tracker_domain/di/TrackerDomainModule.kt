package com.example.tracker_domain.di

import com.example.core.domain.preferences.Preferences
import com.example.tracker_domain.repo.TrackerRepo
import com.example.tracker_domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
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