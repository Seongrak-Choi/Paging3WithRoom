package com.fromu.paging3androomtoyproject.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fromu.paging3androomtoyproject.data.datasource.local.LocalPhotoDataSource
import com.fromu.paging3androomtoyproject.data.datasource.remote.PhotoDataSource
import com.fromu.paging3androomtoyproject.data.datasource.remote.PhotoPagingSourceFactory
import com.fromu.paging3androomtoyproject.data.local.entity.LikedItemEntity
import com.fromu.paging3androomtoyproject.domain.model.Photo
import com.fromu.paging3androomtoyproject.domain.repository.PhotoRepo
import com.fromu.paging3androomtoyproject.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class PhotoRepoImpl @Inject constructor(
    private val photoDataSource: PhotoDataSource,
    private val localPhotoDataSource: LocalPhotoDataSource,
    private val photoPagingSourceFactory: PhotoPagingSourceFactory,
) : PhotoRepo {
    override suspend fun getPhoto(): Flow<Result<List<Photo>>> {
        return photoDataSource.fetchPhotos()
    }

    override fun getPagingPhoto(): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { photoPagingSourceFactory.create() }
        ).flow
    }

    override suspend fun insertLikedItem(item: LikedItemEntity) {
        localPhotoDataSource.insertLikedItem(item)
    }

    override suspend fun deleteLikedItem(itemId: Int) {
        localPhotoDataSource.deleteLikedItem(itemId)
    }

    override fun getLikedItems(): Flow<List<LikedItemEntity>> {
        return localPhotoDataSource.getLikedItems()
    }
}