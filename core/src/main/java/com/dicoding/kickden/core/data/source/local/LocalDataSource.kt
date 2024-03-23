package com.dicoding.kickden.core.data.source.local

import androidx.sqlite.db.SimpleSQLiteQuery
import com.dicoding.kickden.core.data.source.local.entity.SneakersEntity
import com.dicoding.kickden.core.data.source.local.room.SneakersDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val sneakerDao: SneakersDao) {
    fun getListSneakers(query: SimpleSQLiteQuery): Flow<List<SneakersEntity>> = sneakerDao.getListSneakers(query)

    fun getFavoriteSneakers(): Flow<List<SneakersEntity>> = sneakerDao.getFavoriteSneakers()

    suspend fun insertSneakers(sneakersList: List<SneakersEntity>) = sneakerDao.insertSneakers(sneakersList)

    fun deleteAll() = sneakerDao.deleteAll()

    fun updateSneakers(sneaker: SneakersEntity, newState: Boolean) {
        sneaker.isFavorited = newState
        sneakerDao.updateSneakers(sneaker)
    }

}