package com.example.lab4.model

sealed class Routes(val route: String){
    object Home : Routes("home")
    object Favorite: Routes("favorite")
    object Topics: Routes("topics")
}
