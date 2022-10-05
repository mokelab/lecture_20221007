package com.mokelab.mytodo

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mokelab.mytodo.page.create.CreateToDoScreen
import com.mokelab.mytodo.page.create.CreateToDoViewModel
import com.mokelab.mytodo.page.detail.ToDoDetailScreen
import com.mokelab.mytodo.page.detail.ToDoDetailViewModel

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
            }, toDetail = { id ->
                navController.navigate("detail/${id}")
            })
        }

        composable("create") {
            val vm: CreateToDoViewModel = hiltViewModel()
            CreateToDoScreen(vm, back = back)
        }

        composable(
            "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType }),
        ) {
            val vm: ToDoDetailViewModel = hiltViewModel()
            ToDoDetailScreen(vm, back = back)
        }
    }
}