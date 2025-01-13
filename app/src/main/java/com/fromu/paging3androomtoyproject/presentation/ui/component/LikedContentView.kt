package com.fromu.paging3androomtoyproject.presentation.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.ui.unit.dp
import com.fromu.paging3androomtoyproject.domain.model.PhotoWithLikeStatus
import com.fromu.paging3androomtoyproject.presentation.viewmodel.MainViewModel

@Composable
fun LikedContentView(photo: PhotoWithLikeStatus, vm: MainViewModel) {

    var isVisible by remember { mutableStateOf(true) } // 삭제 여부 관리
    var rowHeight by remember { mutableStateOf(Dp.Unspecified) } // Row의 높이를 null로 초기화
    val density = LocalDensity.current

    val animatePadding by animateDpAsState(
        targetValue = if (isVisible) {
            8.dp
        } else {
            0.dp
        }, label = ""
    )

    val animatedHeight by animateDpAsState(
        targetValue = if (isVisible) {
            rowHeight
        } else {
            0.dp // 삭제 시 높이를 0으로 줄임
        },
        finishedListener = { if (it == 0.dp) vm.toggleLike(photo) }, label = "" // 높이가 0이 되면 삭제
    )

    val animatedAlpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f, label = "" // 투명도 애니메이션
    )

    PhotoContent(
        photo = photo,
        onLikeClick = {
            isVisible = false
        },
        modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged { size ->
                with(density) {
                    if (rowHeight == Dp.Unspecified) rowHeight = size.height.toDp()
                }
            }
            .height(animatedHeight)
            .padding(vertical = animatePadding)
            .alpha(animatedAlpha)
    )
}