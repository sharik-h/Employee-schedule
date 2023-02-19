package com.example.employeesheduler.MainPages

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.employeesheduler.viewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun newEvent(
    navHostController: NavHostController,
    viewModel: viewModel,
    use: String
) {

    val event by viewModel.newEvent

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
                    if (event.title != "") {
                        if (use == "newEvent"){
                            viewModel.addNewEvent()
                        }else{
                            viewModel.updateEvent()
                        }
                    }
                    viewModel.clearData()
                    navHostController.navigateUp()
                } ) {
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
                text = "Event scheduling for = ${event.date}",
                modifier = Modifier.padding(top = 18.dp, start = 16.dp)
            )
            TextField(
                value = event.title,
                onValueChange = { viewModel.update(name = "title", value = it) },
                placeholder = { Text(text = "Tile goes here") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black
                )
            )
            TextField(
                value = event.description,
                onValueChange = { viewModel.update(name = "discription", value = it) },
                placeholder = { Text(text = "Discription about event") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black
                )
            )
        }
    }
}