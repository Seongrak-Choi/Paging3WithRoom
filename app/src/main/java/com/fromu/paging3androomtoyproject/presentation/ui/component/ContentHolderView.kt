package com.fromu.paging3androomtoyproject.presentation.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.fromu.paging3androomtoyproject.domain.model.PhotoWithLikeStatus
import com.fromu.paging3androomtoyproject.presentation.Utils.noRippleClickableWithDelay
import com.fromu.paging3androomtoyproject.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun ContentView(photo: PhotoWithLikeStatus, vm: MainViewModel) {
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


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column {
            Box {
                SubcomposeAsyncImage( //사진을 가로 사이즈 만큼 늘리고 높이는 사진 비율에 맞춰서 유동적이게 설정
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    model = photo.photo.src,
                    loading = { //로딩화면 (placeHolder)
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Gray.copy(alpha = 0.1f))
                        )
                    },
                    error = {
                        Text(text = "이미지 로드 실패", modifier = Modifier.align(Alignment.Center))
                    },
                    contentDescription = photo.photo.toString(),
                    contentScale = ContentScale.Crop
                )

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

            Row(modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Photographer",
                        fontSize = 15.sp,
                        color = Color.Black,
                    )

                    Text(
                        text = photo.photo.photographer.toString(),
                        color = Color.Black,
                        fontSize = 12.sp,
                    )
                }

                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(5.dp)
                        .noRippleClickableWithDelay {
                            vm.toggleLike(photo)
                        },
                    imageVector = if (photo.isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "like"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContentViewPreview() {
//    ContentView(PhotoWithLikeStatus(Photo(1, "11", "aa"))
}