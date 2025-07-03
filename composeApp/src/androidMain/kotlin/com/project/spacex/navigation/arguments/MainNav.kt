package com.project.spacex.navigation.arguments

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.project.spacex.screens.MainScreen
import com.project.spacex.navigation.Navigation

fun NavGraphBuilder.addMainScreen(
    modifier: Modifier,
    navController: NavController
) {
    composable(Navigation.MainScreen.route) {
        MainScreen(modifier){ flightId ->
            navController.navigate(
                Navigation.DetailScreen.getRoute(flightId.toString())
            )
        }
    }
}
