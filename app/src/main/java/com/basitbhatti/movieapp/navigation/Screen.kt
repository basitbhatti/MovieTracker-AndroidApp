package com.basitbhatti.movieapp.navigation

sealed class Screen (val route : String) {

    object HomeScreen : Screen("home_screen")

    object LoginScreen : Screen("login_screen")

    object SignupScreen : Screen("signup_screen")

}