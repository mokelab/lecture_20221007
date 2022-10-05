package com.mokelab.mytodo

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mokelab.mytodo.page.create.CreateToDoScreen
import com.mokelab.mytodo.page.create.CreateToDoViewModel

@Composable
fun MyToDoApp() {
    val navController = rememberNavController()
    val back: () -> Unit = {
        navController.popBackStack()
    }

    NavHost(navController = navController, startDestination = "top") {
        composable("top") {
            val vm: MainViewModel = hiltViewModel()
            MainScreen(vm, toCreate = {
                navController.navigate("create")
            })
        }

        composable("create") {
            val vm: CreateToDoViewModel = hiltViewModel()
            CreateToDoScreen(vm, back = back)
        }
    }
}