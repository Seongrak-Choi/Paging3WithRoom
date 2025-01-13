package com.fromu.paging3androomtoyproject.presentation.ui.main.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.fromu.paging3androomtoyproject.domain.model.PhotoWithLikeStatus
import com.fromu.paging3androomtoyproject.presentation.ui.component.MainContentView
import com.fromu.paging3androomtoyproject.presentation.viewmodel.MainViewModel

@Composable
fun HomeScreen(paddingValues: PaddingValues, vm: MainViewModel) {
//    val photoList = remember { mutableStateListOf<Photo>() }
    HomeView(paddingValues, vm)
//    ObservePhotos(vm, photoList)
}


@Composable
fun HomeView(paddingValues: PaddingValues, vm: MainViewModel) {
    val photoPagingItem = vm.pagingPhotos.collectAsLazyPagingItems()
    val likedPhotos by vm.likedPhotosFlow.collectAsState()

    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {

        /*
        paging3 사용하는 경우
         */
        LazyColumn {
            items(photoPagingItem.itemCount) { index ->
                val photo = photoPagingItem[index]
                if (photo != null) {
                    val isLiked = likedPhotos.any { it.id == photo.photo.id }
                    MainContentView(PhotoWithLikeStatus(photo.photo, isLiked), vm)
                }
            }
        }

        /*
        paging3 사용하지 않았을 경우
         */
//        LazyColumn {
//            items(photoList) { photo ->
//                ContentView(photo)
//            }
//        }
    }
}

//@Composable
//fun ObservePhotos(vm: MainViewModel, photoList: MutableList<Photo>) {
//    val photoStatus by vm.photos.collectAsState()
//    val context = LocalContext.current
//    when (photoStatus) {
//        is Result.Success -> {
//            photoList.addAll((photoStatus as Result.Success<List<Photo>>).data.toMutableList())
//        }
//
//        is Result.Error -> {
//            Toast.makeText(context, "통신이 원활하지 않습니다. 잠시 후 시도해 주세요.", Toast.LENGTH_SHORT).show()
//        }
//
//        is Result.Loading -> {
//            LoadingScreen()
//        }
//    }
//}