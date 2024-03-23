package com.dicoding.kickden.detail

import androidx.lifecycle.ViewModel
import com.dicoding.kickden.core.domain.model.Sneakers
import com.dicoding.kickden.core.domain.usecase.SneakersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val sneakersUseCase: SneakersUseCase) : ViewModel() {
    fun addFavorite(sneaker: Sneakers, favoriteStatus: Boolean) {
        sneakersUseCase.setFavoriteSneakers(sneaker,  favoriteStatus)
    }

    fun removeFavorite(sneaker: Sneakers, favoriteStatus: Boolean) {
        sneakersUseCase.setFavoriteSneakers(sneaker,  favoriteStatus)
    }
}