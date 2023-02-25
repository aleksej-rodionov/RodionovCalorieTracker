package com.example.rodionovcalorietracker

import android.app.Application
import com.example.rodionovcalorietracker.di.AppComponent
import com.example.rodionovcalorietracker.di.DaggerAppComponent

class RodionovCalorieTrackerApp: Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().app(this).build()
        appComponent?.inject(this)
    }

    companion object {
        var appComponent: AppComponent? = null
    }
}