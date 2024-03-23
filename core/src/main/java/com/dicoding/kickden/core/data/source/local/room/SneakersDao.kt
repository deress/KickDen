package com.dicoding.kickden.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import com.dicoding.kickden.core.data.source.local.entity.SneakersEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SneakersDao {
    @RawQuery(observedEntities = [SneakersEntity::class])
    fun getListSneakers(query: SupportSQLiteQuery): Flow<List<SneakersEntity>>

    @Query("SELECT * FROM sneakers where favorited = 1")
    fun getFavoriteSneakers(): Flow<List<SneakersEntity>>

    @Query("SELECT EXISTS(SELECT * FROM sneakers WHERE name = :name AND favorited = 1)")
    fun isFavorite(name: String): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSneakers(sneakers: List<SneakersEntity>)

    @Query("DELETE FROM sneakers WHERE favorited = 0")
    fun deleteAll()

    @Update
    fun updateSneakers(sneakers: SneakersEntity)
}