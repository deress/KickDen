package com.dicoding.kickden.core.di

import android.content.Context
import androidx.room.Room
import com.dicoding.kickden.core.data.source.local.room.SneakerDatabase
import com.dicoding.kickden.core.data.source.local.room.SneakersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): SneakerDatabase = Room.databaseBuilder(
        context,
        SneakerDatabase::class.java, "Sneakers.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideTourismDao(database: SneakerDatabase): SneakersDao = database.sneakersDao()
}