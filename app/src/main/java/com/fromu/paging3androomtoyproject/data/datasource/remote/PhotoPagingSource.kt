package com.fromu.paging3androomtoyproject.data.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fromu.paging3androomtoyproject.data.network.api.PhotoApi
import com.fromu.paging3androomtoyproject.domain.model.Photo
import com.fromu.paging3androomtoyproject.domain.model.toDomain

class PhotoPagingSource(
    private val photoApi: PhotoApi
) : PagingSource<Int, Photo>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val currentPage = params.key ?: 1
            val response = photoApi.getPagingPhotos(currentPage, params.loadSize)

            if (response.isSuccessful) {
                val photos = response.body()?.photos?.map { it.toDomain() } ?: emptyList()
                val nextKey = if (photos.isEmpty()) null else currentPage + 1

                LoadResult.Page(
                    data = photos,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(Exception("API Error: ${response.code()} / ${response.message()}"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { state.closestPageToPosition(it)?.prevKey?.plus(1) }
    }
}