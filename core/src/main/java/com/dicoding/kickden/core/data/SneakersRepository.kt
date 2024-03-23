package com.dicoding.kickden.core.data

import com.dicoding.kickden.core.data.source.local.LocalDataSource
import com.dicoding.kickden.core.data.source.remote.RemoteDataSource
import com.dicoding.kickden.core.data.source.remote.network.ApiResponse
import com.dicoding.kickden.core.data.source.remote.response.ListSneakerResponseItem
import com.dicoding.kickden.core.domain.model.Sneakers
import com.dicoding.kickden.core.domain.repository.ISneakersRepository
import com.dicoding.kickden.core.utils.AppExecutors
import com.dicoding.kickden.core.utils.DataMapper
import com.dicoding.kickden.core.utils.SearchUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SneakersRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ISneakersRepository {

    override fun getSneakers(keyword: String): Flow<Resource<List<Sneakers>>> =
        object : NetworkBoundResource<List<Sneakers>, List<ListSneakerResponseItem>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Sneakers>> {
                val query = SearchUtils.getSearchQuery(keyword)
                return localDataSource.getListSneakers(query).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Sneakers>?): Boolean =
                data.isNullOrEmpty()


            override suspend fun createCall(): Flow<ApiResponse<List<ListSneakerResponseItem>>> =
                remoteDataSource.getSneakers()


            override suspend fun saveCallResult(data: List<ListSneakerResponseItem>) {
                val sneakersList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertSneakers(sneakersList)
            }
        }.asFlow()

    override fun getFavoriteSneakers(): Flow<List<Sneakers>> {
        val localData= localDataSource.getFavoriteSneakers()
        return localData.map{
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteSneakers(sneaker: Sneakers, state: Boolean) {
        val sneakersEntity = DataMapper.mapDomainToEntity(sneaker)
        appExecutors.diskIO().execute {
            localDataSource.updateSneakers(sneakersEntity, state)
        }
    }


}