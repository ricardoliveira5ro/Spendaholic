package com.roliveira.spendaholic.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val controller: NavController = rememberNavController()

    Scaffold(

    ) {
        Navigation(viewModel = viewModel, navController = controller, pd = it)
    }
}