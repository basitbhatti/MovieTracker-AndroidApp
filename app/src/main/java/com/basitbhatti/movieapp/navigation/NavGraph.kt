package com.basitbhatti.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.basitbhatti.movieapp.presentation.screens.HomeScreen
import com.basitbhatti.movieapp.presentation.screens.LoginScreen
import com.basitbhatti.movieapp.presentation.screens.SignupScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController, startDestination = Screen.HomeScreen.route
    ) {

        composable(
            route = Screen.HomeScreen.route
        ) {
            HomeScreen(navController = navController)
        }

        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(modifier = Modifier, navController = navController)
        }

        composable(
            route = Screen.SignupScreen.route
        ) {
            SignupScreen(modifier = Modifier, navController = navController)
        }


    }


}