package com.example.employeesheduler.MainPages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.employeesheduler.R
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.employeesheduler.Navigation.Screen
import com.example.employeesheduler.ViewModel.viewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun employerHome(
    viewModel: viewModel,
    navHostController: NavHostController
) {

    viewModel.getAllEmployees()
    val allEmployees by viewModel.allEmployee.observeAsState(initial = emptyList())

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = Color(0xA9B1B9FC))


    Column(Modifier.fillMaxSize()) {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color(0xA9B1B9FC),
            elevation = 0.dp
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Employees",
                    modifier = Modifier.padding(start = 20.dp),
                    fontSize = 20.sp,
                )
                Spacer(modifier = Modifier.weight(0.2f))
                TextButton(onClick = { navHostController.navigate(Screen.newEmployee.route) }) {
                    Text(
                        text = "Add employee",
                        color = Color.Black,
                        fontWeight = FontWeight.Light,
                        fontSize = 18.sp
                    )
                }
            }
        }

        Column(Modifier.fillMaxSize()) {
            LazyColumn {
                items(items = allEmployees) {
                    val delete = SwipeAction(
                        onSwipe = {
                            viewModel.deleteEmployee(id = it.id)
                            viewModel.getAllEmployees()
                        },
                        icon = { Icon(
                            painter = painterResource(id = R.drawable.delete_white),
                            contentDescription = " ",
                            tint = Color.White
                        ) },
                        background = Color.Red
                    )
                    SwipeableActionsBox(
                        endActions = listOf(delete),
                        swipeThreshold = 170.dp,
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                            .clip(RoundedCornerShape(20))
                    ) {
                        employeeModel(name = it.name, uid = it.id) {
                            viewModel.serUid(uid = it.id)
                            navHostController.navigate(Screen.employeeHome.route)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun employeeModel(name: String, uid: String, onClick: () -> Unit ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .size(80.dp)
            .clip(RoundedCornerShape(20))
            .background(Color(0xA9B1B9FC))
            .clickable { onClick() }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 10.dp)) {
            Column {
                Text(text = name, fontSize = 20.sp)
                Text(text = "employ id = $uid", color = Color.Gray)
            }
            Spacer(modifier = Modifier.weight(0.5f))
            Image(painter = painterResource(id = R.drawable.arrow_forward_black), contentDescription = "")
        }
    }

}