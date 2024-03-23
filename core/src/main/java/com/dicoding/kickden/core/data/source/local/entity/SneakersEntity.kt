package com.dicoding.kickden.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "sneakers")
@Parcelize
data class SneakersEntity(
    @field:ColumnInfo(name = "id")
    @field:PrimaryKey
    val id: Int,

    @field:ColumnInfo(name = "color")
    val color: String,

    @field:ColumnInfo(name = "price")
    val price: Int,

    @field:ColumnInfo(name = "name")
    val name: String,

    @field:ColumnInfo(name = "brand")
    val brand: String,

    @field:ColumnInfo("photoUrl")
    val photoUrl: String,

    @field:ColumnInfo(name = "favorited")
    var isFavorited: Boolean? = false,
) : Parcelable

