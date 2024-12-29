package com.fromu.paging3androomtoyproject.presentation.ui.main

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MainBottomNavigation(navHostController: NavHostController) {
    val items = listOf(
        MainBottomNavItem.HOME,
        MainBottomNavItem.Like,
        MainBottomNavItem.DisLike
    )

    NavigationBar {
        val navBackStackEntry = navHostController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        items.forEach { item->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.screenRoute,
                onClick = {
                    if (currentRoute != item.screenRoute) {
                        navHostController.navigate(item.screenRoute) {
                            popUpTo(navHostController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }

}