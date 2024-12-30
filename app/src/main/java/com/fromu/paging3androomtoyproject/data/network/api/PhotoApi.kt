package com.fromu.paging3androomtoyproject.data.network.api

import com.fromu.paging3androomtoyproject.data.model.PhotoRes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {
    @GET("curated?page=1&per_page=40")
    suspend fun getPhotos(): Response<PhotoRes>

    @GET("curated")
    suspend fun getPagingPhotos(
        @Query("page")currentPage: Int,
        @Query("per_page") perPage: Int): Response<PhotoRes>
}