package com.example.employeesheduler.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.employeesheduler.LoginPages.chooseUser
import com.example.employeesheduler.LoginPages.signUp
import com.example.employeesheduler.MainPages.employeeHome
import com.example.employeesheduler.SplashScreen.SplashScreen


@RequiresApi(Build.VERSION_CODES.O)
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
        composable(route = Screen.employeeHome.route){
            employeeHome()
        }
    }
}