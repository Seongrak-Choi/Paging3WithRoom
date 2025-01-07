package com.fromu.paging3androomtoyproject.presentation.ui.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fromu.paging3androomtoyproject.presentation.ui.main.dislike.DisLikeScreen
import com.fromu.paging3androomtoyproject.presentation.ui.main.home.HomeScreen
import com.fromu.paging3androomtoyproject.presentation.ui.main.like.LikeScreen
import com.fromu.paging3androomtoyproject.presentation.viewmodel.MainViewModel

@Composable
fun MainBottomNavGraph(paddingValues: PaddingValues, navController: NavHostController, vm:MainViewModel) {
    NavHost(navController = navController, startDestination = MainBottomNavItem.HOME.screenRoute) {
        composable(MainBottomNavItem.HOME.screenRoute) {
            HomeScreen(paddingValues, vm)
        }
        composable(MainBottomNavItem.Like.screenRoute) {
            LikeScreen(paddingValues, vm)
        }
        composable(MainBottomNavItem.DisLike.screenRoute) {
            DisLikeScreen(paddingValues)
        }
    }
}