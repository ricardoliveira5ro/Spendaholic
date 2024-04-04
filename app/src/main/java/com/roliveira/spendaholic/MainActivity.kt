package com.roliveira.spendaholic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.roliveira.spendaholic.data.DailyWorker
import com.roliveira.spendaholic.ui.MainScreen
import com.roliveira.spendaholic.ui.MainViewModel
import com.roliveira.spendaholic.ui.theme.SpendaholicTheme
import java.util.Calendar
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = Color.White.toArgb()
        window.navigationBarColor = Color.White.toArgb()

        super.onCreate(savedInstanceState)
        setContent {
            SpendaholicTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(viewModel = viewModel)

                    val currentDate = Calendar.getInstance()
                    val dueDate = Calendar.getInstance()

                    // Set Execution at 00:00:00 AM
                    dueDate.set(Calendar.HOUR_OF_DAY, 0)
                    dueDate.set(Calendar.MINUTE, 0)
                    dueDate.set(Calendar.SECOND, 0)

                    // Check if the due date is before the current date, if so, add a day
                    if (dueDate.before(currentDate)) {
                        dueDate.add(Calendar.DAY_OF_MONTH, 1)
                    }

                    // Calculate the time difference between the due date and the current date
                    val timeDiff = dueDate.timeInMillis - currentDate.timeInMillis

                    // Create a OneTimeWorkRequest with the calculated initial delay
                    val dailyWorkRequest = OneTimeWorkRequestBuilder<DailyWorker>()
                        .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
                        //.setConstraints(constraints) // Add constraints if needed
                        .addTag("DailyWorker")
                        .build()

                    // Enqueue the work request
                    WorkManager.getInstance(applicationContext).enqueue(dailyWorkRequest)
                }
            }
        }
    }
}