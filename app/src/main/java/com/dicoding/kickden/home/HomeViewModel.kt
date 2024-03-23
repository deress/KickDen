package com.dicoding.kickden.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.dicoding.kickden.core.domain.usecase.SneakersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val sneakersUseCase: SneakersUseCase) : ViewModel() {
    private val _keyword = MutableLiveData<String>()

    init {
        _keyword.value = ""
    }

    fun search(keyword: String) {
        _keyword.value = keyword
    }

   fun getSneakers() = _keyword.switchMap {
        sneakersUseCase.getSneakers(it).asLiveData()
   }
}