package com.example.employeesheduler.LoginPages

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit


class Authenticate: ComponentActivity() {

    private lateinit var sentOtp: String
    private lateinit var name: String
    private lateinit var user: String
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = intent.getStringExtra("name")!!
        user = intent.getStringExtra("user")!!
        val phone = intent.getStringExtra("phone")
        setContent {
            authPage()
        }

        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber("+91$phone")
            .setTimeout(60, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(
                object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(p0: PhoneAuthCredential) {

                    }

                    override fun onVerificationFailed(p0: FirebaseException) {
                        Toast.makeText(
                            applicationContext, "Verification Failed for some reason",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                        Toast.makeText(
                            applicationContext, "Verification code sent",
                            Toast.LENGTH_SHORT
                        ).show()
                        super.onCodeSent(p0, p1)
                        sentOtp = p0
                    }
                }
            ).build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun signInWithMobile(otp: String) {
        val credentials = PhoneAuthProvider.getCredential(sentOtp, otp)
        firebaseAuth.signInWithCredential(credentials)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = FirebaseAuth.getInstance().currentUser!!
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName("$user:$name").build()
                    currentUser.updateProfile(profileUpdates)
                    finish()
                } else {
                    Toast.makeText(
                        applicationContext, "make sure otp is correct",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    @Composable
    fun authPage() {
        Column(
            modifier = Modifier
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
                onClick = { signInWithMobile(otp) },
                colors = ButtonDefaults.buttonColors(Color.Blue),
                modifier = Modifier
                    .fillMaxWidth()
                    .size(55.dp)
            ) {
                Text(text = "Verify", color = Color.White)
            }
        }
    }
}