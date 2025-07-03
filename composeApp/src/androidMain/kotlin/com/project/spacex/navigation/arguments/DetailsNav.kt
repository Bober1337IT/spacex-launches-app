package com.project.spacex.navigation.arguments

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.project.spacex.screens.DetailScreen
import com.project.spacex.navigation.Navigation

fun NavGraphBuilder.addDetailsScreen(
    modifier: Modifier,
    navController: NavController
) {
    composable(
        Navigation.DetailScreen.route,
        arguments = listOf(navArgument("flightId") { type = NavType.IntType })
    ) { navBackStackEntry ->
        val flightId = navBackStackEntry.arguments?.getInt("flightId")
        DetailScreen(modifier, flightId) {
            navController.popBackStack()
        }
    }
}