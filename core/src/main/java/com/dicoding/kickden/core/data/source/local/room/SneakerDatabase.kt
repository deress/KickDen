package com.dicoding.kickden.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.kickden.core.data.source.local.entity.SneakersEntity

@Database(entities = [SneakersEntity::class], version = 1, exportSchema = false)
abstract class SneakerDatabase: RoomDatabase() {
    abstract fun sneakersDao(): SneakersDao
}