package com.example.lab4

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lab4.model.*
import com.example.lab4.ui.theme.BlogTheme
import com.example.lab4.ui.theme.Lab4Theme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BlogArticle()
                }
            }
        }
    }
}

// app main composable
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BlogArticle(){
    // declaring NavController
    val navController = rememberNavController(/*navigators = */)
    Scaffold(
        // including bottom app bar
        content = { NavigationHandler(navController = navController) },
        bottomBar = { BottomBar(navController = navController)}
    )

}

// function to handle navigation
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun NavigationHandler(
    navController: NavHostController,
){
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ){
        // Home composable
        composable(Routes.Home.route){
            Home(sampleArticles)
        }

        // Favorite composable
        composable(Routes.Favorite.route){
            Favorite(sampleArticles)
        }

        // Topics composable
        composable(Routes.Topics.route){
            Topics()
        }
    }
}

// Bottom bar navigation
// Reference: https://www.geeksforgeeks.org/bottom-navigation-bar-in-android-jetpack-compose/
@Composable
fun BottomBar(navController: NavHostController){
    BottomNavigation {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        // handling state for each item selected on bottom bar
        BottomNavItems.BottomItems.forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(imageVector = navItem.image,
                        contentDescription = navItem.title)
                },
                label = {
                    Text(text = navItem.title)
                },
            )
        }

    }
}