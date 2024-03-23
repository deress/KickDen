package com.dicoding.kickden.core.di

import com.dicoding.kickden.core.data.SneakersRepository
import com.dicoding.kickden.core.domain.repository.ISneakersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(sneakersRepository: SneakersRepository): ISneakersRepository
}