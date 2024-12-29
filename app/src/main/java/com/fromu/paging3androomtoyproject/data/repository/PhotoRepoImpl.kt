package com.fromu.paging3androomtoyproject.data.repository

import com.fromu.paging3androomtoyproject.data.datasource.remote.PhotoDataSource
import com.fromu.paging3androomtoyproject.data.model.Photos
import com.fromu.paging3androomtoyproject.domain.model.Photo
import com.fromu.paging3androomtoyproject.domain.model.toDomain
import com.fromu.paging3androomtoyproject.domain.repository.PhotoRepo
import kotlinx.coroutines.flow.Flow
import com.fromu.paging3androomtoyproject.domain.utils.Result
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class PhotoRepoImpl @Inject constructor(
    private val photoDataSource: PhotoDataSource
) : PhotoRepo {
    override suspend fun getPhoto(): Flow<Result<List<Photo>>> = flow {
        try {
            emit(Result.Loading)
            val response = photoDataSource.fetchPhotos()
            if (response.isSuccessful) {
                val photos = response.body()?.photos?.map { it.toDomain() } ?: emptyList()
                emit(Result.Success(photos))
            } else {
                emit(Result.Error("API Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Result.Error("Network Exception: ${e.message}", e))
        }
    }
}