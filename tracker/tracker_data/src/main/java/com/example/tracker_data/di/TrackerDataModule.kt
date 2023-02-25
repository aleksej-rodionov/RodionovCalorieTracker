package com.example.tracker_data.di

import android.app.Application
import androidx.room.Room
import com.example.tracker_data.local.TrackerDao
import com.example.tracker_data.local.TrackerDatabase
import com.example.tracker_data.remote.OpenFoodApi
import com.example.tracker_data.repo.TrackerRepoImpl
import com.example.tracker_domain.repo.TrackerRepo
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
class TrackerDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenFoodApi(client: OkHttpClient): OpenFoodApi {
        return Retrofit.Builder()
            .baseUrl(OpenFoodApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideTrackerDatabase(app: Application) : TrackerDatabase {
        return Room.databaseBuilder(app, TrackerDatabase::class.java, "tracker_db").build()
    }

    @Provides
    @Singleton
    fun provideTrackerRepo(db: TrackerDatabase, api: OpenFoodApi): TrackerRepo {
        return TrackerRepoImpl(db.trackerDao, api)
    }
}