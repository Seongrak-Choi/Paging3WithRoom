package com.fromu.paging3androomtoyproject.presentation.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MainBottomNavItem(val title: String, val icon: ImageVector, val screenRoute: String) {
    data object HOME :
            MainBottomNavItem("Home", Icons.Default.Home, "Home")
    data object Like :
            MainBottomNavItem("Like", Icons.Default.Favorite, "Like")
    data object DisLike :
            MainBottomNavItem("DisLike", Icons.Default.Close, "DisLike")
}