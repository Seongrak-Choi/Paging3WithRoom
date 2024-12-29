package com.fromu.paging3androomtoyproject.data.network.api

import com.fromu.paging3androomtoyproject.data.model.PhotoRes
import retrofit2.Response
import retrofit2.http.GET

interface PhotoApi {
    @GET("curated?page=1&per_page=40")
    suspend fun getData(): Response<PhotoRes>
}