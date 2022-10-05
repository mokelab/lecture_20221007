package com.mokelab.mytodo

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyToDoApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "top") {
        composable("top") {
            val vm: MainViewModel = hiltViewModel()
            MainScreen(vm, toCreate = {
                navController.navigate("create")
            })
        }
    }
}