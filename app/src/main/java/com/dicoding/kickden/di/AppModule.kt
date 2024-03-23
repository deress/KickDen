package com.dicoding.kickden.di

import com.dicoding.kickden.core.domain.usecase.SneakersInteractor
import com.dicoding.kickden.core.domain.usecase.SneakersUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideSneakersUseCase(sneakersInteractor: SneakersInteractor): SneakersUseCase
}