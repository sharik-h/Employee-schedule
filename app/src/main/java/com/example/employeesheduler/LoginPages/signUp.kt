package com.example.employeesheduler.LoginPages

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun signUp(user: String) {

    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    val context = LocalContext.current

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
        Button(onClick = {
                    context.startActivity(
                        Intent(context, Authenticate::class.java)
                            .putExtra("name", name)
                            .putExtra("phone", phone)
                            .putExtra("user", user)
                    )
        }, modifier = Modifier
            .fillMaxWidth()
            .size(55.dp), colors = ButtonDefaults.buttonColors(Color.Blue)
        ) {
            Text(text = "Sign up", color = Color.White)
        }
    }

}