package com.fromu.paging3androomtoyproject.data.datasource.remote

import com.fromu.paging3androomtoyproject.data.model.PhotoRes
import com.fromu.paging3androomtoyproject.data.network.api.PhotoApi
import retrofit2.Response
import javax.inject.Inject

class PhotoDataSource @Inject constructor(
    val photoApi: PhotoApi
) {
    suspend fun fetchPhotos(): Response<PhotoRes> {
        return photoApi.getPhotos()
    }
}