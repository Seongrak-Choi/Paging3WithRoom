package com.fromu.paging3androomtoyproject.data.datasource.remote

import com.fromu.paging3androomtoyproject.data.remote.api.PhotoApi
import javax.inject.Inject

class PhotoPagingSourceFactory @Inject constructor(
    private val photoApi: PhotoApi
){
    fun create(): PhotoPagingSource {
        return PhotoPagingSource(photoApi)
    }
}