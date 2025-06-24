package com.example.live_audio_room

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.live_audio_room.pages.HomePage
import com.example.live_audio_room.screen.AuthScreen
import com.example.live_audio_room.screen.HomeScreen
import com.example.live_audio_room.screen.SignupScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    GlobalNavigation.navController=navController

    val isLoggedIn = Firebase.auth.currentUser != null
    val firstPage = if(isLoggedIn)"home" else "auth"

    NavHost(navController=navController , startDestination = firstPage){

        composable("home") {
            HomeScreen(modifier , navController)
        }

        composable("auth") {
            AuthScreen(modifier , navController)
        }

        composable("login") {
            LoginScreen(modifier , navController)
        }
        composable("signup") {
            SignupScreen(modifier , navController)
        }
    }

}

object  GlobalNavigation{
    lateinit var navController: NavHostController
}
