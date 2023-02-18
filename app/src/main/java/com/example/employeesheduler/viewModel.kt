package com.example.employeesheduler

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.employeesheduler.Data.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class viewModel: ViewModel() {

    val currentuser = FirebaseAuth.getInstance().currentUser?.uid
    val firestore = Firebase.firestore
    val newEvent = mutableStateOf(Event())


    fun addNewEvent() {
        firestore
            .document("$currentuser/${newEvent.value.date}")
            .set(newEvent.value)
    }

    fun update(name: String, value: String) {
        when(name) {
            "title" -> newEvent.value = newEvent.value.copy(title = value)
            "discription" -> newEvent.value = newEvent.value.copy(description = value)
            "date" -> newEvent.value = newEvent.value.copy(date = value)
        }
    }

}