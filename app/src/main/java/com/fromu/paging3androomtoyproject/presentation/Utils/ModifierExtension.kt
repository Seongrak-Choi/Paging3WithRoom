package com.fromu.paging3androomtoyproject.presentation.Utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    this.clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) {
            onClick()
        }

}


/**
 * 중복 클릭 방지 확장 함수
 *
 * @param delayMillis
 * @param onClick
 * @return
 */
inline fun Modifier.noRippleClickableWithDelay(
    delayMillis: Long = 500L,
    crossinline onClick: () -> Unit
): Modifier = composed {
    var lastEventTimeMs by remember { mutableLongStateOf(0L) }

    this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        val now = System.currentTimeMillis()
        if (now - lastEventTimeMs >= delayMillis) {
            onClick()
            lastEventTimeMs = now
        }
    }
}
