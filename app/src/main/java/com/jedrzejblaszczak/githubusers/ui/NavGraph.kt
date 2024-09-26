package com.jedrzejblaszczak.githubusers.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jedrzejblaszczak.githubusers.ui.users.UsersViewModel
import com.jedrzejblaszczak.githubusers.ui.users.details.UserDetailsScreen
import com.jedrzejblaszczak.githubusers.ui.users.list.UsersListScreen

@Composable
fun AppNavGraph(usersViewModel: UsersViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "main") {
        composable("main") {
            UsersListScreen(navController, usersViewModel)
        }
        composable("detail/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toInt() ?: 0
            UserDetailsScreen(userId, usersViewModel)
        }
    }
}
