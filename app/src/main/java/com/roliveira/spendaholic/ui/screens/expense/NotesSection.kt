package com.roliveira.spendaholic.ui.screens.expense

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.fonts.Typography

@Composable
fun NotesSection(
    noteState: String,
    onNoteChange: (String) -> Unit
) {
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

        val maxChars = 30
        TextField(
            value = noteState,
            onValueChange = { if(it.length <= maxChars) onNoteChange(it) },
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
}