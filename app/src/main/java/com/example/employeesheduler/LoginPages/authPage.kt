package com.example.employeesheduler.LoginPages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class Authenticate: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        setContent {
            authPage()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun authPage() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
    ) {

        var otp by remember { mutableStateOf("") }

        Text(
            text = "Verify OTP",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(text = "Enter the otp sended to you number.", color = Color.Gray)
        OutlinedTextField(
            value = otp,
            onValueChange = { otp = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Blue,
                backgroundColor = Color.LightGray,
                cursorColor = Color.Blue
            )
        )
        Spacer(modifier = Modifier.height(25.dp))
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(Color.Blue),
            modifier = Modifier
                .fillMaxWidth()
                .size(55.dp)
        ) {
            Text(text = "Verify", color = Color.White)
        }
    }
}