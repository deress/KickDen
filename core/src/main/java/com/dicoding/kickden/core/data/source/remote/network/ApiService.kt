package com.dicoding.kickden.core.data.source.remote.network


import com.dicoding.kickden.core.data.source.remote.response.ListSneakerResponseItem
import retrofit2.http.GET

interface ApiService {
    @GET("sneakers")
    suspend fun getListSneaker(): List<ListSneakerResponseItem>
}