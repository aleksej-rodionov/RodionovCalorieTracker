package com.example.rodionovcalorietracker.di

import android.app.Application
import com.example.rodionovcalorietracker.MainActivity
import com.example.rodionovcalorietracker.RodionovCalorieTrackerApp
import com.example.tracker_data.di.TrackerDataModule
import com.example.tracker_domain.di.TrackerDomainModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton

//@AppScope
@Singleton
@Component(
    modules = [
        AppModule::class,
        TrackerDataModule::class,
        TrackerDomainModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(app: RodionovCalorieTrackerApp)
    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(app: Application): Builder
        fun build(): AppComponent
    }
}

@Scope
annotation class AppScope