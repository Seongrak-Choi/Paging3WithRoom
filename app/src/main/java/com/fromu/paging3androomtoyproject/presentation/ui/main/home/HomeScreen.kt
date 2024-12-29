package com.fromu.paging3androomtoyproject.presentation.ui.main.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.fromu.paging3androomtoyproject.domain.model.Photo
import com.fromu.paging3androomtoyproject.domain.utils.Result
import com.fromu.paging3androomtoyproject.presentation.ui.component.ContentView
import com.fromu.paging3androomtoyproject.presentation.ui.component.LoadingScreen
import com.fromu.paging3androomtoyproject.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(paddingValues: PaddingValues, vm: MainViewModel) {
    val photoList = remember { mutableStateListOf<Photo>() }

    Init(vm)
    HomeView(paddingValues, photoList)
    ObservePhotos(vm, photoList)
}

@Composable
fun Init(vm: MainViewModel) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            vm.fetchPhotos()
        }
    }
}

@Composable
fun HomeView(paddingValues: PaddingValues, photoList: MutableList<Photo>) {
    Box(modifier = Modifier
        .padding(paddingValues)
        .fillMaxSize()) {
        LazyColumn {
            items(photoList) { photo ->
                ContentView(photo)
            }
        }
    }
}

@Composable
fun ObservePhotos(vm: MainViewModel, photoList: MutableList<Photo>) {
    val photoStatus by vm.photos.collectAsState()
    val context = LocalContext.current
    when (photoStatus) {
        is Result.Success -> {
            photoList.addAll((photoStatus as Result.Success<List<Photo>>).data.toMutableList())
        }

        is Result.Error -> {
            Toast.makeText(context, "통신이 원활하지 않습니다. 잠시 후 시도해 주세요.", Toast.LENGTH_SHORT).show()
        }

        is Result.Loading -> {
            LoadingScreen()
        }
    }
}