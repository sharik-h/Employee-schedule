package com.example.employeesheduler

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employeesheduler.Data.Employee
import com.example.employeesheduler.Data.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class viewModel: ViewModel() {

    val currentuser = FirebaseAuth.getInstance().currentUser?.uid
    val firestore = Firebase.firestore
    val newEvent = mutableStateOf(Event())
    val newEmployee = mutableStateOf(Employee())
    var allEvents: MutableLiveData<List<Event>> = MutableLiveData()
    var allEmployee: MutableLiveData<List<Employee>> = MutableLiveData()


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
            "id" -> newEvent.value = newEvent.value.copy(id = value)
            "empId" -> newEmployee.value = newEmployee.value.copy(id = value)
            "empName" -> newEmployee.value = newEmployee.value.copy(name = value)
        }
    }

    fun getAllEvents() {
        firestore
            .collection("$currentuser")
            .get()
            .addOnSuccessListener {
                val data = mutableListOf<Event>()
                it.documents.forEach {
                   val doc =  it.toObject(Event::class.java)!!
                    doc.id = it.id
                    data.add(doc)
                }
                allEvents.value = data
            }
    }

    fun deleteEvent(id: String){
        firestore
            .document("$currentuser/$id")
            .delete()
    }

    fun updateEvent() {
        firestore
            .document("$currentuser/${newEvent.value.id}")
            .update(mapOf(
                "title" to newEvent.value.title,
                "description" to newEvent.value.description
            ))
    }

    fun clearData(){
        newEvent.value = Event()
        newEmployee.value = Employee()
    }

    fun getAllEmployees() {
        firestore
            .collection("employer/$currentuser/employees")
            .get()
            .addOnSuccessListener {
                val data = mutableListOf<Employee>()
                it.documents.forEach {
                    data.add(it.toObject(Employee::class.java)!!)
                }
                allEmployee.value = data
            }
    }

    fun addNewEmployee() {
        firestore
            .document("employer/$currentuser/employees/${newEmployee.value.id}")
            .set(newEmployee.value)
    }

}