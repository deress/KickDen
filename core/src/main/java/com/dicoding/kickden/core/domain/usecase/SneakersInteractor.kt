package com.dicoding.kickden.core.domain.usecase


import com.dicoding.kickden.core.domain.model.Sneakers
import com.dicoding.kickden.core.domain.repository.ISneakersRepository
import javax.inject.Inject

class SneakersInteractor @Inject constructor(private val sneakersRepository: ISneakersRepository): SneakersUseCase {
    override fun getSneakers(keyword : String) = sneakersRepository.getSneakers(keyword)

    override fun getFavoriteSneakers() = sneakersRepository.getFavoriteSneakers()

    override fun setFavoriteSneakers(sneaker: Sneakers, state: Boolean) =
        sneakersRepository.setFavoriteSneakers(sneaker, state)

}