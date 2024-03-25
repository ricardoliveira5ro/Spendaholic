package com.roliveira.spendaholic.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.ui.theme.SpendaholicTheme
import java.time.Month

@Composable
fun Dropdown() {
    val months = Month.entries.map { month -> month.toString().lowercase().replaceFirstChar { it.uppercase() } }
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    var showDropdown by rememberSaveable { mutableStateOf(false) }

    val contentShape = if (!showDropdown) {
        RoundedCornerShape(16.dp)
    } else {
        RoundedCornerShape(0.dp)
    }

    Column(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .clip(contentShape)
                .width(120.dp)
                .background(color = colorResource(id = R.color.blue_dropdown))
                .border(width = 1.dp, color = colorResource(id = R.color.light_grey), shape = contentShape)
                .clickable { showDropdown = true },
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = months[selectedIndex],
                    color = colorResource(id = R.color.light_grey),
                    modifier = Modifier.padding(start = 5.dp, top = 5.dp, end = 0.dp, bottom = 5.dp),
                    fontSize = 13.sp
                )

                Icon(
                    painter = painterResource(id = R.drawable.arrow_down),
                    contentDescription = "Arrow Down",
                    tint = colorResource(id = R.color.light_grey)
                )
            }
        }

        Box(
        ) {
            if (showDropdown) {
                Popup(
                    alignment = Alignment.TopCenter,
                    properties = PopupProperties(
                        excludeFromSystemGesture = true,
                    ),
                    onDismissRequest = { showDropdown = false }
                ) {
                    Column(
                        modifier = Modifier
                            .width(120.dp)
                            .border(width = 1.dp, color = colorResource(id = R.color.light_grey)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        months.onEachIndexed { index, item ->
                            if (index != 0) {
                                Divider(thickness = 1.dp, color = Color.LightGray)
                            }
                            Box(
                                modifier = Modifier
                                    .background(colorResource(id = R.color.blue_dropdown))
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedIndex = index
                                        showDropdown = !showDropdown
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = item,
                                    color = colorResource(id = R.color.light_grey),
                                    modifier = Modifier.padding(4.dp),
                                    fontSize = 13.sp
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DropdownPreview() {
    SpendaholicTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Dropdown()
        }
    }
}