package com.fromu.paging3androomtoyproject.presentation.utils

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object AnimationUtils {
    @Composable
    fun lazyColumnDeleteItemAnimatePadding(isDelete: Boolean): State<Dp> {
        return animateDpAsState(
            targetValue = if (isDelete) 0.dp else 8.dp,
            label = "PaddingAnimation"
        )
    }

    @Composable
    fun lazyColumnDeleteItemAnimatedHeight(
        isDelete: Boolean,
        rowHeight: Dp,
        onFinish: () -> Unit = {}
    ): State<Dp> {
        return animateDpAsState(
            targetValue = if (isDelete) 0.dp else rowHeight,
            finishedListener = { if (it == 0.dp) onFinish() },
            label = "HeightAnimation"
        )
    }

    @Composable
    fun lazyColumnDeleteItemAnimateAlpha(isDelete: Boolean): State<Float> {
        return animateFloatAsState(
            targetValue = if (isDelete) 0f else 1f,
            label = "AlphaAnimation"
        )
    }
}