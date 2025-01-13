package com.fromu.paging3androomtoyproject.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fromu.paging3androomtoyproject.domain.model.PhotoWithLikeStatus
import com.fromu.paging3androomtoyproject.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun MainContentView(photo: PhotoWithLikeStatus, vm: MainViewModel) {
    val scope = rememberCoroutineScope()
    var isBottomSheetVisible by remember { mutableStateOf(false) }

    if (isBottomSheetVisible) {
        BottomSheetDialog(
            onRemoveClickListener = {
                scope.launch {
                    //삭제 로직
                }
            },
            onDismiss = { isBottomSheetVisible = false }
        )
    }

    PhotoContent(
        photo = photo,
        onLikeClick = {
            vm.toggleLike(photo)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        additionalContent = {
            Icon(
                Icons.Default.Menu, contentDescription = "Menu",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .align(Alignment.TopEnd)
                    .size(width = 40.dp, height = 50.dp)
                    .clickable {
                        isBottomSheetVisible = true
                    },
                tint = Color.White
            )
        }
    )
}

