package com.example.employeesheduler.LoginPages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun chooseUser() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Who are you?",
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Center,
            verticalAlignment = CenterVertically,
            modifier = Modifier
                .width(200.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(25))
                .background(Color(0xFF2891EC))
        ) {
            Text(text = "I am an employer")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Center,
            verticalAlignment = CenterVertically,
            modifier = Modifier
                .width(200.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(25))
                .background(Color(0xFF2891EC))
        ) {
            Text(text = "I am an employee")
        }
    }

}