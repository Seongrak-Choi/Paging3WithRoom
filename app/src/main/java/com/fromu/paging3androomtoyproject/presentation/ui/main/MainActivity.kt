package com.fromu.paging3androomtoyproject.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fromu.paging3androomtoyproject.presentation.ui.theme.Paging3andRoomToyProjectTheme
import com.fromu.paging3androomtoyproject.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Paging3andRoomToyProjectTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(vm: MainViewModel = hiltViewModel()) {
    val navHostController = rememberNavController()

    Scaffold(
        bottomBar = { MainBottomNavigation(navHostController) }
    ) {
        MainView(it, navHostController, vm)
    }
}

@Composable
fun MainView(paddingValues: PaddingValues, navHostController: NavHostController, vm: MainViewModel) {
    MainBottomNavGraph(paddingValues, navHostController, vm)
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
//    MainScreen()
}