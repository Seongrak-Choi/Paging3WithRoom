package com.fromu.paging3androomtoyproject.data.datasource.local

import com.fromu.paging3androomtoyproject.data.local.dao.LikedItemDao
import com.fromu.paging3androomtoyproject.data.local.entity.LikedItemEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LocalPhotoDataSource @Inject constructor(
    private val likedItemDao: LikedItemDao
) {

    fun getLikedItems(): Flow<List<LikedItemEntity>> {
        return likedItemDao.getLikedItems()
    }

    suspend fun insertLikedItem(photo: LikedItemEntity) {
        likedItemDao.insertLikedItem(photo)
    }

    suspend fun deleteLikedItem(photoId: Int) {
        likedItemDao.deleteLikedItem(photoId)
    }
}