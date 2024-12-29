package com.fromu.paging3androomtoyproject.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetDialog(onRemoveClickListener: () -> Unit, onDismiss: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = null,
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        // Sheet content
        Box(modifier = Modifier.padding(horizontal = 10.dp, vertical = 30.dp).fillMaxWidth()) {
            Text("Dislike", fontSize = 15.sp, modifier = Modifier.clickable {
                onRemoveClickListener()
                onDismiss()
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetDialogPreview() {
    BottomSheetDialog(onRemoveClickListener = {}, onDismiss = {})
}