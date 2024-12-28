package com.fromu.paging3androomtoyproject.ui.main.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(paddingValues: PaddingValues) {
    Box(modifier = Modifier.padding(paddingValues)) {
        Text("HomeScreen")
    }
}