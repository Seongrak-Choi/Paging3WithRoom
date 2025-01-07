package com.fromu.paging3androomtoyproject.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.fromu.paging3androomtoyproject.data.local.entity.LikedItemEntity
import com.fromu.paging3androomtoyproject.domain.model.PhotoWithLikeStatus
import com.fromu.paging3androomtoyproject.domain.repository.PhotoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val photoRepo: PhotoRepo) : ViewModel() {
//    private val _photos = MutableStateFlow<Result<List<Photo>>>(Result.Loading)
//    val photos: StateFlow<Result<List<Photo>>> = _photos

    private val _likedPhotosFlow = MutableStateFlow<List<LikedItemEntity>>(emptyList())
    val likedPhotosFlow: StateFlow<List<LikedItemEntity>> get() = _likedPhotosFlow

    val pagingPhotos: Flow<PagingData<PhotoWithLikeStatus>> = photoRepo.getPagingPhoto()
        .map { pagingData ->
            pagingData.map { photo ->
                val isLiked = likedPhotosFlow.value.any { it.id == photo.id }
                PhotoWithLikeStatus(photo, isLiked)
            }
        }.cachedIn(viewModelScope)




    init {
        viewModelScope.launch {
            photoRepo.getLikedItems().collect { likedItems ->
                _likedPhotosFlow.value = likedItems
            }
        }
    }

//    private fun fetchPhotos() {
//        viewModelScope.launch {
//            photoRepo.getPhoto().collect { result ->
//                _photos.value = result
//            }
//        }
//    }

    fun toggleLike(photo: PhotoWithLikeStatus) {
        val photoId = photo.photo.id ?: return

        viewModelScope.launch {
            if (photo.isLiked) {
                photoRepo.deleteLikedItem(photoId)
            } else {
                photoRepo.insertLikedItem(
                    LikedItemEntity(
                        id = photoId,
                        photographer = photo.photo.photographer ?: "Unknown Photographer",
                        src = photo.photo.src ?: ""
                    )
                )
            }
        }
    }
}