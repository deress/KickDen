package com.dicoding.kickden.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sneakers
(
    val id: Int,
    val color: String,
    val price: Int,
    val name: String,
    val brand: String,
    val photoUrl: String,
    var isFavorited: Boolean? = false,
) : Parcelable
