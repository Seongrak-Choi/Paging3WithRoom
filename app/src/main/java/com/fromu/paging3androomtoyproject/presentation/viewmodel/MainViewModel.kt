package com.fromu.paging3androomtoyproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fromu.paging3androomtoyproject.domain.model.Photo
import com.fromu.paging3androomtoyproject.domain.repository.PhotoRepo
import com.fromu.paging3androomtoyproject.domain.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val photoRepo: PhotoRepo): ViewModel() {
    private val _photos = MutableStateFlow<Result<List<Photo>>>(Result.Loading)
    val photos: StateFlow<Result<List<Photo>>> = _photos

    fun fetchPhotos() {
        viewModelScope.launch {
            photoRepo.getPhoto().collect { result ->
                _photos.value = result
            }
        }
    }
}