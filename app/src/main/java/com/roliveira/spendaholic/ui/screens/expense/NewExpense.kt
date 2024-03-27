package com.roliveira.spendaholic.ui.screens.expense

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.roliveira.spendaholic.DummyData
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.fonts.Typography
import com.roliveira.spendaholic.ui.theme.SpendaholicTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NewExpense() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "New Expense",
            color = Color.Black,
            fontFamily = Typography.sanFranciscoText,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Amount",
                color = Color.Black,
                fontFamily = Typography.sanFranciscoRounded,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                var amountState by remember { mutableStateOf("") }
                TextField(
                    value = amountState,
                    onValueChange = { if(it.matches(Regex("^\\d*(\\.\\d{0,2})?\$"))) { amountState = it } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontFamily = Typography.sanFranciscoRounded,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    ),
                    leadingIcon = {
                       Icon(
                           painter = painterResource(id = R.drawable.dollar),
                           contentDescription = "Dollar symbol",
                           tint = colorResource(id = R.color.blue),
                           modifier = Modifier.size(28.dp)
                       )
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
            }
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Select category",
                color = Color.Black,
                fontFamily = Typography.sanFranciscoRounded,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )

            var selectedCategoryIndex by rememberSaveable { mutableIntStateOf(0) }
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                DummyData.categories.onEachIndexed { index, category ->

                    val isSelected = selectedCategoryIndex == index
                    Row(
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .background(
                                color = if (isSelected) colorResource(id = R.color.box_selected)
                                else colorResource(id = R.color.light_grey_boxes),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .border(
                                width = if (isSelected) 1.dp else 0.dp,
                                color = if (isSelected) colorResource(id = R.color.blue)
                                else Color.Transparent,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                            .height(32.dp)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                selectedCategoryIndex = index
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = category.icon),
                            contentDescription = "Entertainment category",
                            modifier = Modifier.size(22.dp),
                            tint = if (isSelected) colorResource(id = R.color.blue) else Color.Black
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = category.name,
                            color = if (isSelected) colorResource(id = R.color.blue) else Color.Black,
                            fontFamily = Typography.sanFranciscoRounded,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Small note",
                color = Color.Black,
                fontFamily = Typography.sanFranciscoRounded,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )

            var noteState by remember { mutableStateOf("") }
            val maxChars = 30
            TextField(
                value = noteState,
                onValueChange = { if(it.length <= maxChars) noteState = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontFamily = Typography.sanFranciscoRounded,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(id = R.color.light_grey_boxes),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = colorResource(id = R.color.light_grey_boxes),
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledContainerColor = colorResource(id = R.color.light_grey_boxes),
                    disabledIndicatorColor = Color.Transparent
                ),
                supportingText = {
                    Text(
                        text = "${noteState.length} / $maxChars",
                        color = Color.Black,
                        fontFamily = Typography.sanFranciscoRounded,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                }
            )
        }

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
            var date by remember { mutableStateOf("") }
            val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

            val timePickerState = rememberTimePickerState()
            var showTimePicker by remember { mutableStateOf(false) }
            var time by remember { mutableStateOf("") }
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
                            tint = colorResource(id = R.color.blue),
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
                            tint = colorResource(id = R.color.blue),
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
                                val selectedDate = Calendar.getInstance().apply {
                                    timeInMillis = datePickerState.selectedDateMillis!!
                                }
                                date = dateFormatter.format(selectedDate.time)
                                showDatePicker = false
                            }
                        ) { Text("OK") }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                showDatePicker = false
                            }
                        ) { Text("Cancel") }
                    }
                ) {
                    DatePicker(state = datePickerState)
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
                                time = timeFormatter.format(selectedTime.time)
                                showTimePicker = false
                            }
                        ) { Text("OK") }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                showTimePicker = false
                            }
                        ) { Text("Cancel") }
                    },
                ) {
                    TimePicker(state = timePickerState)
                }
            }
        }
    }
}

@Composable
fun TimePickerDialog(
    title: String = "Select Time",
    onDismissRequest: () -> Unit,
    confirmButton: @Composable (() -> Unit),
    dismissButton: @Composable (() -> Unit)? = null,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = containerColor
                ),
            color = containerColor
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    dismissButton?.invoke()
                    confirmButton()
                }
            }
        }
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
            NewExpense()
        }
    }
}