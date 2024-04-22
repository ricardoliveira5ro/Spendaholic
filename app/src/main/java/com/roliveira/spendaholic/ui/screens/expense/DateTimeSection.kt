package com.roliveira.spendaholic.ui.screens.expense

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.fonts.Typography
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimeSection(
    date: String,
    time: String,
    onDateSelected: (String) -> Unit,
    onTimeSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Date",
            color = Color.Black,
            fontFamily = Typography.sanFranciscoRounded,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
        )

        val datePickerState = rememberDatePickerState()
        var showDatePicker by remember { mutableStateOf(false) }
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        val timePickerState = rememberTimePickerState()
        var showTimePicker by remember { mutableStateOf(false) }
        val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())

        TextField(
            value = if(date == "" && time == "") "" else "$date, $time",
            onValueChange = {  },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(
                color = Color.Black,
                fontFamily = Typography.sanFranciscoRounded,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp
            ),
            leadingIcon = {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.schedule),
                        contentDescription = "Schedule icon",
                        tint = colorResource(id = R.color.dark_blue),
                        modifier = Modifier
                            .size(22.dp)
                            .clickable {
                                showDatePicker = true
                            }
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Icon(
                        painter = painterResource(id = R.drawable.time),
                        contentDescription = "Second icon",
                        tint = colorResource(id = R.color.dark_blue),
                        modifier = Modifier
                            .size(18.dp)
                            .clickable {
                                showTimePicker = true
                            }
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorResource(id = R.color.light_grey_boxes),
                focusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = colorResource(id = R.color.light_grey_boxes),
                unfocusedIndicatorColor = Color.Transparent,
                disabledContainerColor = colorResource(id = R.color.light_grey_boxes),
                disabledIndicatorColor = Color.Transparent
            )
        )

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val selectedMillis = datePickerState.selectedDateMillis
                            if (selectedMillis != null) {
                                val selectedDate = Calendar.getInstance().apply {
                                    timeInMillis = selectedMillis
                                }
                                onDateSelected(dateFormatter.format(selectedDate.time))
                            }

                            showDatePicker = false
                        }
                    ) { Text("OK", color = colorResource(id = R.color.dark_blue)) }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDatePicker = false
                        }
                    ) { Text("Cancel", color = colorResource(id = R.color.dark_blue)) }
                }
            ) {
                DatePicker(
                    state = datePickerState,
                    colors = DatePickerDefaults.colors(
                        todayContentColor = colorResource(id = R.color.dark_blue),
                        todayDateBorderColor = colorResource(id = R.color.dark_blue),
                        selectedDayContentColor = Color.White,
                        dayContentColor = colorResource(id = R.color.dark_blue),
                        selectedDayContainerColor = colorResource(id = R.color.dark_blue),
                        selectedYearContentColor = Color.White,
                        selectedYearContainerColor = colorResource(id = R.color.dark_blue),
                        currentYearContentColor = colorResource(id = R.color.dark_blue)
                    )
                )
            }
        }

        if (showTimePicker) {
            TimePickerDialog(
                onDismissRequest = { showTimePicker = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val selectedTime = Calendar.getInstance().apply {
                                set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                                set(Calendar.MINUTE, timePickerState.minute)
                            }
                            onTimeSelected(timeFormatter.format(selectedTime.time))
                            showTimePicker = false
                        }
                    ) { Text("OK", color = colorResource(id = R.color.dark_blue)) }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showTimePicker = false
                        }
                    ) { Text("Cancel", color = colorResource(id = R.color.dark_blue)) }
                },
            ) {
                TimePicker(
                    state = timePickerState,
                    colors = TimePickerDefaults.colors(
                        selectorColor = colorResource(id = R.color.dark_blue),
                        periodSelectorSelectedContainerColor = colorResource(id = R.color.dark_blue),
                        periodSelectorSelectedContentColor = Color.White
                    )
                )
            }
        }
    }
}