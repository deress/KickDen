package com.dicoding.kickden.di

import com.dicoding.kickden.core.domain.usecase.SneakersUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

    fun sneakersUseCase(): SneakersUseCase
}