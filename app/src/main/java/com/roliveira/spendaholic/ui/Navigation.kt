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
import com.roliveira.spendaholic.ui.screens.expense.Expense
import com.roliveira.spendaholic.ui.screens.schedule.Schedule
import com.roliveira.spendaholic.ui.screens.settings.Settings
import com.roliveira.spendaholic.ui.screens.summary.Summary
import com.roliveira.spendaholic.utils.Utils

@Composable
fun Navigation(viewModel: MainViewModel, navController: NavController, pd: PaddingValues) {
    NavHost(navController = navController as NavHostController, startDestination = Screen.Dashboard.route, modifier = Modifier.padding(pd)) {
        composable(Screen.Dashboard.route) {
            Dashboard(
                expenses = viewModel.expenses.value.orEmpty(),
                onNewExpenseClick = { viewModel.navigateTo(Screen.Expense.route + "/-1") },
                onScheduleClick = { viewModel.navigateTo(Screen.Schedule.route) },
                onSummaryClick = { viewModel.navigateTo(Screen.Summary.route) },
                onTransactionClick = { expenseId ->
                    viewModel.navigateTo(Screen.Expense.route + "/$expenseId")
                }
            )
        }

        composable(Screen.Stats.route) {

        }

        composable(Screen.Settings.route) {
            Settings(
                settings = Utils.validateSettings(viewModel.settings.value),
                onNavigateBack = { navController.navigateUp() },
                onSaveSettings = viewModel::saveSettings
            )
        }

        composable(Screen.Expense.route + "/{expenseId}") { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("expenseId")?.toInt() ?: -1
            val expense = viewModel.expenses.value?.find { it.id == id } ?: Utils.defaultExpense()

            Expense(
                expense = expense,
                onNavigateBack = { navController.navigateUp() },
                onNavigateToDashboard = { viewModel.navigateTo(Screen.Dashboard.route) },
                onSaveExpense = viewModel::saveExpense,
                onDeleteExpense = {
                    viewModel.deleteExpense(expense.id)
                    navController.navigateUp()
                }
            )
        }

        composable(Screen.Schedule.route) {
            Schedule(
                expenses =  viewModel.expenses.value.orEmpty(),
                onNavigateBack = { navController.navigateUp() },
                onNewExpense = { viewModel.navigateTo(Screen.Expense.route  + "/-1") },
                onExpenseClick = { expenseId ->
                    viewModel.navigateTo(Screen.Expense.route + "/$expenseId")
                }
            )
        }

        composable(Screen.Summary.route) {
            Summary(
                expenses = viewModel.expenses.value.orEmpty()
            )
        }
    }
}