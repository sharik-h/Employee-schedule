package com.example.employeesheduler.SplashScreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.employeesheduler.Navigation.Screen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController) {
    var startAnimState by remember { mutableStateOf(false) }
    val floatAsState = animateFloatAsState(
        targetValue = if(startAnimState) 1f else 0f,
        animationSpec = tween(1000)
    )

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = Color(0xFFFFFFFF))

    LaunchedEffect(key1 = true ) {
        startAnimState = true
        val user = FirebaseAuth.getInstance().currentUser?.displayName
        delay(1000)
        navHostController.popBackStack()
        if (user != null) {
            user.split(":")
            if (user.contains("employee")){
                navHostController.navigate(Screen.employeeHome.route)
            }else{
                navHostController.navigate(Screen.employerHome.route)
            }
        }else{
            navHostController.navigate(Screen.chooseUser.route)
        }
    }
    Splash(floatAsState.value)
}

@Composable
fun Splash(floatAsState: Float) {
    Column(modifier = Modifier
        .fillMaxSize()
        .alpha(alpha = floatAsState)
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Employee Shedule",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue
        )
    }

}