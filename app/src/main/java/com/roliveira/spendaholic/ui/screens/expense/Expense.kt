package com.roliveira.spendaholic.ui.screens.expense

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.model.Category
import com.roliveira.spendaholic.model.Expense
import com.roliveira.spendaholic.ui.theme.SpendaholicTheme
import com.roliveira.spendaholic.utils.Utils
import java.util.Date

@Composable
fun Expense(
    expense: Expense,
    onNavigateBack: () -> Unit,
    onNavigateToDashboard: () -> Unit,
    onSaveExpense:(Int, Float, Int, String, String, String) -> Unit
) {
    val isNewExpense = expense.id == -1

    var amountState by remember { mutableStateOf(if (isNewExpense) "" else expense.amount.toString()) }

    var selectedCategoryIndex by rememberSaveable {
        mutableIntStateOf(if (isNewExpense) 0 else expense.category.id - 1)
    }

    var noteState by remember { mutableStateOf(if (isNewExpense) "" else expense.note ?: "") }

    var date by remember { mutableStateOf(if (isNewExpense) "" else Utils.dateToString(expense.date)) }
    var time by remember { mutableStateOf(if (isNewExpense) "" else Utils.timeToString(expense.date)) }

    var showSheet by remember { mutableStateOf(false) }
    var repeatOption by remember { mutableStateOf("Not repeatable") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExpenseHeader(onNavigateBack)

        AmountSection(
            amountState = amountState,
            onAmountChange = { amountState = it }
        )

        CategorySection(
            selectedCategoryIndex = selectedCategoryIndex,
            onSelectedCategoryIndexChange = { selectedCategoryIndex = it }
        )

        NotesSection(
            noteState = noteState,
            onNoteChange = { noteState = it }
        )

        DateTimeSection(
            date = date,
            time = time,
            onDateSelected = { date = it },
            onTimeSelected = { time = it }
        )

        Spacer(modifier = Modifier.height(40.dp))

        RepeatAndSubmitSection(
            onSaveClick = {
                onSaveExpense(expense.id, amountState.toFloat(), selectedCategoryIndex + 1, noteState, date, time)
                onNavigateToDashboard()
            },
            onRepeatClick = { showSheet = true },
            showSheet = showSheet,
            repeatOption = repeatOption,
            onDismiss = { showSheet = false },
            onOptionSelected = { option -> repeatOption = option }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NewExpensePreview() {
    SpendaholicTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val dummyExpense = Expense(
                id = 1,
                category = Category(5, "Education", R.drawable.education_category, Color(0xFFFFFFFF)),
                note = "Sample Note",
                amount = 100.0f,
                date = Date()
            )

            Expense(
                expense = dummyExpense,
                onNavigateBack = {},
                onNavigateToDashboard = {},
                onSaveExpense = { _, _, _, _, _, _ -> }
            )
        }
    }
}