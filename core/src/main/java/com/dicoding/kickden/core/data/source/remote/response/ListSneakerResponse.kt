package com.dicoding.kickden.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListSneakerResponseItem(
	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("color")
	val color: String,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("brand")
	val brand: String,

	@field:SerializedName("photoUrl")
	val photoUrl: String,
) : Parcelable
