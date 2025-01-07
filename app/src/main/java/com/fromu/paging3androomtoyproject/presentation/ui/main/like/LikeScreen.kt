package com.fromu.paging3androomtoyproject.presentation.ui.main.like

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.fromu.paging3androomtoyproject.domain.model.Photo
import com.fromu.paging3androomtoyproject.domain.model.PhotoWithLikeStatus
import com.fromu.paging3androomtoyproject.presentation.ui.component.ContentView
import com.fromu.paging3androomtoyproject.presentation.viewmodel.MainViewModel

@Composable
fun LikeScreen(paddingValues: PaddingValues, vm: MainViewModel) {
    val likedPhotos by vm.likedPhotosFlow.collectAsState()

    Box(modifier = Modifier
        .padding(paddingValues)
        .fillMaxSize()) {
        LazyColumn() {

            items(count = likedPhotos.size, key = { likedPhotos[it].id }) { index ->
                val photo = likedPhotos[index]
                ContentView(PhotoWithLikeStatus(Photo(photo.id, photo.src, photo.photographer), true), vm)
            }
        }
    }
}