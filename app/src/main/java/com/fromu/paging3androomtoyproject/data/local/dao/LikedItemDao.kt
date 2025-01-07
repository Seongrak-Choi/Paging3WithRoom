package com.fromu.paging3androomtoyproject.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fromu.paging3androomtoyproject.data.local.entity.LikedItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LikedItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLikedItem(item: LikedItemEntity)

    @Query("SELECT * FROM liked_items")
    fun getLikedItems(): Flow<List<LikedItemEntity>>

    @Query("DELETE FROM liked_items WHERE id = :itemId")
    suspend fun deleteLikedItem(itemId: Int)
}