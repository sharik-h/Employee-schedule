package com.example.employeesheduler

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employeesheduler.Data.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class viewModel: ViewModel() {

    val currentuser = FirebaseAuth.getInstance().currentUser?.uid
    val firestore = Firebase.firestore
    val newEvent = mutableStateOf(Event())
    var allEvents: MutableLiveData<List<Event>> = MutableLiveData()


    fun addNewEvent() {
        firestore
            .collection("$currentuser")
            .add(newEvent.value)
    }

    fun update(name: String, value: String) {
        when(name) {
            "title" -> newEvent.value = newEvent.value.copy(title = value)
            "discription" -> newEvent.value = newEvent.value.copy(description = value)
            "date" -> newEvent.value = newEvent.value.copy(date = value)
        }
    }

    fun getAllEvents() {
        firestore
            .collection("$currentuser")
            .get()
            .addOnSuccessListener {
                val data = mutableListOf<Event>()
                it.documents.forEach {
                    data.add(it.toObject(Event::class.java)!!)
                }
                allEvents.value = data
            }
    }

}