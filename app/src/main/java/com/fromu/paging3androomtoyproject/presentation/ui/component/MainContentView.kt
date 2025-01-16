package com.fromu.paging3androomtoyproject.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fromu.paging3androomtoyproject.domain.model.PhotoWithLikeStatus
import com.fromu.paging3androomtoyproject.presentation.utils.AnimationUtils
import com.fromu.paging3androomtoyproject.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun MainContentView(photo: PhotoWithLikeStatus, vm: MainViewModel) {
    val scope = rememberCoroutineScope()
    var isBottomSheetVisible by remember { mutableStateOf(false) }

    var isDelete by remember { mutableStateOf(false) } // 삭제 여부 관리
    var rowHeight by remember { mutableStateOf(Dp.Unspecified) } // Row의 높이를 null로 초기화
    val density = LocalDensity.current


    val animatedPadding = AnimationUtils.lazyColumnDeleteItemAnimatePadding(isDelete)
    val animatedHeight = AnimationUtils.lazyColumnDeleteItemAnimatedHeight(isDelete, rowHeight) {
        //TODO pager에서 해당 아이템 삭제 처리.
    }
    val animatedAlpha = AnimationUtils.lazyColumnDeleteItemAnimateAlpha(isDelete)


    if (isBottomSheetVisible) {
        BottomSheetDialog(
            onRemoveClickListener = {
                scope.launch {
                    //바텀 시트 delet 클릭 시
                    isDelete = true
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
            .padding(vertical = 8.dp)
            .onSizeChanged { size ->
                with(density) {
                    if (rowHeight == Dp.Unspecified) rowHeight = size.height.toDp()
                }
            }
            .height(animatedHeight.value)
            .padding(vertical = animatedPadding.value)
            .alpha(animatedAlpha.value),
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

