package com.example.employeesheduler.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.employeesheduler.LoginPages.chooseUser
import com.example.employeesheduler.LoginPages.signUp
import com.example.employeesheduler.SplashScreen.SplashScreen


@Composable
fun loginNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.splash.route
    ){
        composable(route = Screen.splash.route){
            SplashScreen(navHostController = navHostController)
        }
        composable(route = Screen.chooseUser.route){
          chooseUser(navHostController = navHostController)
        }
        composable(
            route = Screen.signUp.route,
            arguments = listOf(navArgument(name = "user"){type = NavType.StringType})
        ){
            signUp(user = it.arguments?.getString("user").toString())
        }
    }
}