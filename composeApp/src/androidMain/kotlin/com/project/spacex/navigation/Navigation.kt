package com.project.spacex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.project.spacex.navigation.arguments.addDetailsScreen
import com.project.spacex.navigation.arguments.addMainScreen

sealed class Navigation(var route: String) {
    data object MainScreen : Navigation("main_screen")
    data object DetailScreen : Navigation("detail_screen/{flightId}") {
        fun getRoute(flightId: String) = "detail_screen/$flightId"
    }
}

@Composable
fun ArgumentScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
){
    NavHost(
        navController = navHostController,
        startDestination = Navigation.MainScreen.route
    ) {
        addMainScreen(modifier, navHostController)
        addDetailsScreen(modifier, navHostController)
    }
}
