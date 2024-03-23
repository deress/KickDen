package com.dicoding.kickden.core.domain.repository

import com.dicoding.kickden.core.data.Resource
import com.dicoding.kickden.core.domain.model.Sneakers
import kotlinx.coroutines.flow.Flow

interface ISneakersRepository {
    fun getSneakers(keyword : String): Flow<Resource<List<Sneakers>>>

    fun getFavoriteSneakers(): Flow<List<Sneakers>>

    fun setFavoriteSneakers(sneaker: Sneakers, state: Boolean)
}