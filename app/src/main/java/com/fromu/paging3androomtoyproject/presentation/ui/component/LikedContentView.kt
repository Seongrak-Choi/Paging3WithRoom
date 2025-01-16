package com.fromu.paging3androomtoyproject.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.fromu.paging3androomtoyproject.domain.model.PhotoWithLikeStatus
import com.fromu.paging3androomtoyproject.presentation.utils.AnimationUtils
import com.fromu.paging3androomtoyproject.presentation.viewmodel.MainViewModel

@Composable
fun LikedContentView(photo: PhotoWithLikeStatus, vm: MainViewModel) {

    var isDelete by remember { mutableStateOf(false) } // 삭제 여부 관리
    var rowHeight by remember { mutableStateOf(Dp.Unspecified) } // Row의 높이를 null로 초기화
    val density = LocalDensity.current


    val animatedPadding = AnimationUtils.lazyColumnDeleteItemAnimatePadding(isDelete)
    val animatedHeight = AnimationUtils.lazyColumnDeleteItemAnimatedHeight(isDelete, rowHeight) {
        vm.toggleLike(photo)
    }
    val animatedAlpha = AnimationUtils.lazyColumnDeleteItemAnimateAlpha(isDelete)

    PhotoContent(
        photo = photo,
        onLikeClick = {
            isDelete = true
        },
        modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged { size ->
                with(density) {
                    if (rowHeight == Dp.Unspecified) rowHeight = size.height.toDp()
                }
            }
            .height(animatedHeight.value)
            .padding(vertical = animatedPadding.value)
            .alpha(animatedAlpha.value)
    )
}