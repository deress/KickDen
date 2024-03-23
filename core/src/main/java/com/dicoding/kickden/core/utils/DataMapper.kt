package com.dicoding.kickden.core.utils

import com.dicoding.kickden.core.data.source.local.entity.SneakersEntity
import com.dicoding.kickden.core.data.source.remote.response.ListSneakerResponseItem
import com.dicoding.kickden.core.domain.model.Sneakers

object DataMapper {
    fun mapResponsesToEntities(input: List<ListSneakerResponseItem>): List<SneakersEntity> {
        val sneakersList = ArrayList<SneakersEntity>()
        input.map {
            val sneakers = SneakersEntity(
                id = it.id,
                brand = it.brand,
                name = it.name,
                price = it.price,
                color = it.color,
                photoUrl = it.photoUrl,
                isFavorited = false
            )
            sneakersList.add(sneakers)
        }
        return sneakersList
    }

    fun mapEntitiesToDomain(input: List<SneakersEntity>): List<Sneakers> =
        input.map {
            Sneakers(
                id = it.id,
                brand = it.brand,
                name = it.name,
                price = it.price,
                color = it.color,
                photoUrl = it.photoUrl,
                isFavorited = it.isFavorited,
            )
        }

    fun mapDomainToEntity(input: Sneakers) = SneakersEntity(
        id = input.id,
        brand = input.brand,
        name = input.name,
        price = input.price,
        color = input.color,
        photoUrl = input.photoUrl,
        isFavorited = input.isFavorited,

    )
}