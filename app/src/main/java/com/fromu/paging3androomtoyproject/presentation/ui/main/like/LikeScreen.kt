package com.fromu.paging3androomtoyproject.presentation.ui.main.like

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.fromu.paging3androomtoyproject.domain.model.Photo
import com.fromu.paging3androomtoyproject.domain.model.PhotoWithLikeStatus
import com.fromu.paging3androomtoyproject.presentation.ui.component.LikedContentView
import com.fromu.paging3androomtoyproject.presentation.viewmodel.MainViewModel

@Composable
fun LikeScreen(paddingValues: PaddingValues, vm: MainViewModel) {
    val likedPhotos by vm.likedPhotosFlow.collectAsState()

    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {


        LazyColumn() {
            items(count = likedPhotos.size, key = { likedPhotos[it].id }) {
                val photo = likedPhotos[it]
                LikedContentView(PhotoWithLikeStatus(Photo(photo.id, photo.src, photo.photographer), true), vm)
            }
        }
    }
}
