package com.dicoding.kickden.favorite.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.kickden.core.domain.usecase.SneakersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(sneakersUseCase: SneakersUseCase) : ViewModel() {
    val listSneakers = sneakersUseCase.getFavoriteSneakers().asLiveData()

}