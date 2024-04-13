package com.roliveira.spendaholic.ui.screens.settings.currency

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roliveira.spendaholic.data.Currencies
import com.roliveira.spendaholic.fonts.Typography
import com.roliveira.spendaholic.model.Currency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetCurrency(
    onDismiss: () -> Unit,
    onOptionSelected: (Currency) -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    val currencies = Currencies.currencies

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
            items(currencies) {
                Text(
                    text = it.toString(),
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