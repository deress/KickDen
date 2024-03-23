package com.dicoding.kickden.core.data.source.remote

import android.util.Log
import com.dicoding.kickden.core.data.source.remote.network.ApiResponse
import com.dicoding.kickden.core.data.source.remote.network.ApiService
import com.dicoding.kickden.core.data.source.remote.response.ListSneakerResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getSneakers(): Flow<ApiResponse<List<ListSneakerResponseItem>>> {
        return flow {
            try {
                val response = apiService.getListSneaker()
                val dataArray = response
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}