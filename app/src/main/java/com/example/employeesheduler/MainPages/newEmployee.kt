package com.example.employeesheduler.MainPages

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.employeesheduler.viewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun newEmployee(
    viewModel: viewModel,
    navHostController: NavHostController ) {

    val newEmployee by viewModel.newEmployee

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
                    text = "New event",
                    modifier = Modifier.padding(start = 20.dp),
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.weight(0.2f))
                TextButton(onClick = {
                    viewModel.addNewEmployee()
                    viewModel.clearData()
                    navHostController.navigateUp()
                }) {
                    Text(
                        text = "Save",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }
        }
        Column(Modifier.fillMaxSize()) {
            Text(
                text = "Employee Id",
                color = Color.LightGray,
                modifier = Modifier.padding(top = 18.dp, start = 16.dp)
            )
            TextField(
                value = newEmployee.id,
                onValueChange = { viewModel.update(name = "empId", value = it) },
                modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    unfocusedIndicatorColor = Color(0xA98187BB),
                    focusedIndicatorColor = Color(0xA9B1B9FC),
                    cursorColor = Color.Black
                )
            )
            Text(
                text = "Name",
                color = Color.LightGray,
                modifier = Modifier.padding(top = 18.dp, start = 16.dp)
            )
            TextField(
                value = newEmployee.name,
                onValueChange = { viewModel.update(name = "empName", value = it) },
                modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    unfocusedIndicatorColor = Color(0xA98187BB),
                    focusedIndicatorColor = Color(0xA9B1B9FC),
                    cursorColor = Color.Black
                )
            )
        }
    }
}