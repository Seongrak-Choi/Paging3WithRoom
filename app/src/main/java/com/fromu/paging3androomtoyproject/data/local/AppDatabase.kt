package com.fromu.paging3androomtoyproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fromu.paging3androomtoyproject.data.local.dao.LikedItemDao
import com.fromu.paging3androomtoyproject.data.local.entity.LikedItemEntity

@Database(entities = [LikedItemEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun likedItemDao(): LikedItemDao
}