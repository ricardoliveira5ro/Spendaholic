package com.roliveira.spendaholic.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.roliveira.spendaholic.ui.screens.dashboard.Dashboard
import com.roliveira.spendaholic.ui.screens.expense.NewExpense
import com.roliveira.spendaholic.utils.Utils

@Composable
fun Navigation(viewModel: MainViewModel, navController: NavController, pd: PaddingValues) {
    NavHost(navController = navController as NavHostController, startDestination = Screen.Dashboard.route, modifier = Modifier.padding(pd)) {
        composable(Screen.Dashboard.route) {
            Dashboard(
                expenses = viewModel.expenses.value.orEmpty(),
                onNewExpenseClick = { viewModel.navigateTo(Screen.NewExpense.route + "/-1") },
                onTransactionClick = { expenseId ->
                    viewModel.navigateTo(Screen.NewExpense.route + "/$expenseId")
                }
            )
        }

        composable(Screen.Stats.route) {

        }

        composable(Screen.Settings.route) {

        }

        composable(Screen.NewExpense.route + "/{expenseId}") { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("expenseId")?.toInt() ?: -1
            val expense = viewModel.expenses.value?.find { it.id == id } ?: Utils.defaultExpense()

            NewExpense(
                expense = expense,
                onNavigateBack = { navController.navigateUp() },
                onNavigateToDashboard = { viewModel.navigateTo(Screen.Dashboard.route) },
                onSaveExpense = viewModel::saveExpense
            )
        }
    }
}