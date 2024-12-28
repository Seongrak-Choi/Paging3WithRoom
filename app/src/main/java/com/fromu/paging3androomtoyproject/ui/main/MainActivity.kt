package com.fromu.paging3androomtoyproject.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fromu.paging3androomtoyproject.ui.theme.Paging3andRoomToyProjectTheme

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
fun MainScreen() {
    val navHostController = rememberNavController()

    Scaffold(
        bottomBar = { MainBottomNavigation(navHostController) }
    ) {
        MainView(it, navHostController)
    }
}

@Composable
fun MainView(paddingValues: PaddingValues, navHostController: NavHostController) {
    MainBottomNavGraph(paddingValues, navHostController)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Paging3andRoomToyProjectTheme {
        MainScreen()
    }
}