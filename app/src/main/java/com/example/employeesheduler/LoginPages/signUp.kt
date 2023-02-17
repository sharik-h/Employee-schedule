package com.example.employeesheduler.LoginPages

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview(showBackground = true)
@Composable
fun signUp() {

    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)) {

        Text(
            text = "Sign up",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(text = "Name", color = Color.Gray)
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Blue,
                backgroundColor = Color.LightGray,
                cursorColor = Color.Blue
            )
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(text = "Phone", color = Color.Gray)
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Blue,
                backgroundColor = Color.LightGray,
                cursorColor = Color.Blue
            )
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = { /*TODO*/ }, modifier = Modifier
            .fillMaxWidth()
            .size(55.dp), colors = ButtonDefaults.buttonColors(Color.Blue)
        ) {
            Text(text = "Sign up", color = Color.White)
        }
    }

}