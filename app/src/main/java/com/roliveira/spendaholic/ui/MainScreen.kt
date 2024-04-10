package com.roliveira.spendaholic.ui

import android.app.Activity
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.ui.screens.BottomBar

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val navigateToScreen by viewModel.navigateToScreen.observeAsState()

    navigateToScreen?.let { screenRoute ->
        controller.navigate(screenRoute)
        viewModel.navigationDone()
    }

    val view = LocalView.current
    val window = (view.context as Activity).window

    if (currentRoute == "dashboard") {
        window.statusBarColor = colorResource(id = R.color.dark_blue).toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false

    } else {
        window.statusBarColor = Color.White.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
    }

    viewModel.loadExpenses()

    Scaffold(
        bottomBar = {
            if(currentRoute == Screen.Dashboard.route || currentRoute == Screen.Settings.route || currentRoute == Screen.Stats.route || currentRoute == Screen.Summary.route)
                BottomBar(viewModel = viewModel, currentRoute = currentRoute)
        }
    ) {
        Navigation(viewModel = viewModel, navController = controller, pd = it)
    }
}