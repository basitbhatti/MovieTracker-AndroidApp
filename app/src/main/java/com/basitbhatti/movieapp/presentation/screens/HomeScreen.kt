package com.basitbhatti.movieapp.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.basitbhatti.movieapp.navigation.Screen
import com.basitbhatti.movieapp.presentation.viewmodel.AuthenticationViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun HomeScreen(
    navController: NavHostController,
) {

    val auth = Firebase.auth

    if (auth.currentUser == null) {
        navController.navigate(Screen.LoginScreen.route) {
            popUpTo(Screen.HomeScreen.route) { inclusive = true }
        }
    } else {

    }



}