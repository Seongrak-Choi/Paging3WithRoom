package com.fromu.paging3androomtoyproject.data.datasource.remote

import com.fromu.paging3androomtoyproject.data.network.api.PhotoApi
import com.fromu.paging3androomtoyproject.domain.model.Photo
import com.fromu.paging3androomtoyproject.domain.model.toDomain
import com.fromu.paging3androomtoyproject.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoDataSource @Inject constructor(
    val photoApi: PhotoApi
) {
    suspend fun fetchPhotos(): Flow<Result<List<Photo>>> =
        flow {
            try {
                emit(Result.Loading)
                val response = photoApi.getPhotos()
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