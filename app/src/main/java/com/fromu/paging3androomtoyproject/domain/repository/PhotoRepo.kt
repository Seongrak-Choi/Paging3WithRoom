package com.fromu.paging3androomtoyproject.domain.repository

import androidx.paging.PagingData
import com.fromu.paging3androomtoyproject.data.local.entity.LikedItemEntity
import com.fromu.paging3androomtoyproject.domain.model.Photo
import com.fromu.paging3androomtoyproject.domain.model.PhotoWithLikeStatus
import com.fromu.paging3androomtoyproject.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface PhotoRepo {
    suspend fun getPhoto(): Flow<Result<List<Photo>>>
    fun getPagingPhoto(): Flow<PagingData<Photo>>

    suspend fun insertLikedItem(item: LikedItemEntity)
    suspend fun deleteLikedItem(itemId: Int)
    fun getLikedItems(): Flow<List<LikedItemEntity>>
}