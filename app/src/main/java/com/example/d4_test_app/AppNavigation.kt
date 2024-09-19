package com.example.d4_test_app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.d4_test_app.screen.detailsInfo.DetailsInfoScreen
import com.example.d4_test_app.screen.mainList.MainScreen


enum class D4Screen() {
    MainScreen,
    DetailsInfoScreen,
}

sealed class NavigationItem(val route: String) {
    object Main : NavigationItem(D4Screen.MainScreen.name)
    object DetailsInfo : NavigationItem(D4Screen.DetailsInfoScreen.name)
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Main.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Main.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(NavigationItem.Main.route)
            }
            MainScreen(
                navController = navController,
                viewModel = hiltViewModel(),
                navigationViewModel = hiltViewModel(parentEntry)
            )
        }
        composable(
            NavigationItem.DetailsInfo.route
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(NavigationItem.Main.route)
            }
            DetailsInfoScreen(
                navController = navController,
                navigationViewModel = hiltViewModel(parentEntry)
            )
        }
    }
}





