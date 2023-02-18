package com.example.employeesheduler.Navigation

sealed class Screen(val route: String) {
    object chooseUser: Screen(route = "chooseUser")
    object signUp: Screen(route = "signUp/{user}"){
        fun passUser(user: String) = "signUp/$user"
    }
    object splash: Screen(route = "splash")
    object employeeHome: Screen(route = "employeeHome")
    object newEvent: Screen(route = "newEvent")
}
