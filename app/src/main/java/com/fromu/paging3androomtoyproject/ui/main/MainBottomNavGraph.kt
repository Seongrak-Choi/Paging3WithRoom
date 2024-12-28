package com.fromu.paging3androomtoyproject.ui.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fromu.paging3androomtoyproject.ui.main.dislike.DisLikeScreen
import com.fromu.paging3androomtoyproject.ui.main.home.HomeScreen
import com.fromu.paging3androomtoyproject.ui.main.like.LikeScreen

@Composable
fun MainBottomNavGraph(paddingValues: PaddingValues, navController: NavHostController) {
    NavHost(navController = navController, startDestination = MainBottomNavItem.HOME.screenRoute) {
        composable(MainBottomNavItem.HOME.screenRoute) {
            HomeScreen(paddingValues)
        }
        composable(MainBottomNavItem.Like.screenRoute) {
            LikeScreen(paddingValues)
        }
        composable(MainBottomNavItem.DisLike.screenRoute) {
            DisLikeScreen(paddingValues)
        }
    }
}