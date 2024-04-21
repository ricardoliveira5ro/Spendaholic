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
import com.roliveira.spendaholic.ui.screens.settings.categories.Category
import com.roliveira.spendaholic.ui.screens.settings.categories.ManageCategories
import com.roliveira.spendaholic.ui.screens.stats.Stats
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
            Stats(expenses = viewModel.expenses.value.orEmpty())
        }

        composable(Screen.Settings.route) {
            Settings(
                settings = Utils.validateSettings(viewModel.settings.value),
                onNavigateBack = { navController.navigateUp() },
                onSaveSettings = viewModel::saveSettings,
                onManageCategories = { viewModel.navigateTo(Screen.ManageCategories.route) }
            )
        }

        composable(Screen.Expense.route + "/{expenseId}") { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("expenseId")?.toInt() ?: -1
            val expense = viewModel.expenses.value?.find { it.id == id } ?: Utils.defaultExpense()

            Expense(
                expense = expense,
                categories = viewModel.settings.value?.categories.orEmpty(),
                onNavigateBack = { navController.navigateUp() },
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

        composable(Screen.ManageCategories.route) {
            ManageCategories(
                categories = viewModel.settings.value?.categories.orEmpty(),
                onNavigateBack = { navController.navigateUp() },
                onNewCategory = { viewModel.navigateTo(Screen.Category.route + "/-1") },
                onCategoryClick = { categoryId ->
                    viewModel.navigateTo(Screen.Category.route + "/$categoryId")
                },
                onDeleteCategory = viewModel::deleteCategory
            )
        }

        composable(Screen.Category.route + "/{categoryId}") { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("categoryId")?.toInt() ?: -1
            val category = viewModel.settings.value?.categories?.find { it.id == id } ?: Utils.defaultCategory()

            Category(
                category = category,
                onNavigateBack = { navController.navigateUp() },
                onSaveCategory = viewModel::saveCategory
            )
        }
    }
}