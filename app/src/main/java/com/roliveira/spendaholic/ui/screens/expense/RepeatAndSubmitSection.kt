package com.roliveira.spendaholic.ui.screens.expense

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.fonts.Typography

@Composable
fun RepeatAndSubmitSection(
    onSaveClick: () -> Unit,
    onRepeatClick: () -> Unit,
    showSheet: Boolean,
    repeatOption: String,
    onDismiss: () -> Unit,
    onOptionSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            modifier = Modifier
                .weight(1f),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.dark_blue)),
            shape = RoundedCornerShape(8.dp),
            onClick = { onSaveClick() }
        ) {
            Text(
                text = "Save",
                color = Color.White,
                fontFamily = Typography.sanFranciscoRounded,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        IconButton(
            onClick = onRepeatClick,
            modifier = Modifier.border(1.dp, colorResource(id = R.color.dark_blue), RoundedCornerShape(6.dp))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.repeat),
                contentDescription = "Repeat icon",
                modifier = Modifier.size(28.dp)
            )
        }

        if (showSheet) {
            BottomSheet(
                onDismiss = onDismiss,
                onOptionSelected = onOptionSelected
            )
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = repeatOption,
            color = Color.Black,
            fontFamily = Typography.sanFranciscoRounded,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    onDismiss: () -> Unit,
    onOptionSelected: (String) -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    val repeatTimestamps = listOf("Not repeatable", "Every Day", "Every Week", "Every Month", "Every Year")

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 52.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(repeatTimestamps) {
                Text(
                    text = it,
                    color = Color.Black,
                    fontFamily = Typography.sanFranciscoRounded,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .clickable {
                            onOptionSelected(it)
                            onDismiss()
                        }
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                )
            }
        }
    }
}