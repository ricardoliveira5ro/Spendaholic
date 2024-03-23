package com.roliveira.spendaholic.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.roliveira.spendaholic.ui.screens.BottomBar

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = { BottomBar(currentRoute = currentRoute, controller = controller) }
    ) {
        Navigation(viewModel = viewModel, navController = controller, pd = it)
    }
}