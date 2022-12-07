package com.example.lab4.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

object BottomNavItems {

    val BottomItems = listOf(
        BottomItem(
            title = "Home",
            image = Icons.Filled.Home,
            route = "home"
        ),
        BottomItem(
            title = "Favorites",
            image = Icons.Filled.Favorite,
            route = "favorite"
        ),
        BottomItem(
            title = "Article Topics",
            image = Icons.Filled.Edit,
            route = "topics"
        )

    )
}