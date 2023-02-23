package com.example.rodionovcalorietracker

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavController
import com.example.core.domain.preferences.Preferences
import com.example.rodionovcalorietracker.repo.TrackerRepoFake
import com.example.tracker_domain.use_case.TrackerUseCases
import com.example.tracker_presentation.search.SearchViewModel
import com.example.tracker_presentation.tracker_overview.TrackerOverviewViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule

@HiltAndroidTest
class TrackerOverviewE2E {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var repoFake: TrackerRepoFake
    private lateinit var trackerUseCases: TrackerUseCases
    private lateinit var prefs: Preferences
    private lateinit var trackerOverviewViewModel: TrackerOverviewViewModel
    private lateinit var searchViewModel: SearchViewModel

    private lateinit var navController: NavController

    @Before
    fun setUp() {
        hiltRule.inject() // here we finally inject our dependencies into this DaggerHilt test class

    }
}