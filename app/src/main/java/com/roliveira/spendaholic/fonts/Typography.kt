package com.roliveira.spendaholic.fonts

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.roliveira.spendaholic.R

object Typography {
    val sanFrancisco = FontFamily(
        Font(R.font.sf_pro_rounded_light, FontWeight.Light),
        Font(R.font.sf_pro_rounded_regular, FontWeight.Normal),
        Font(R.font.sf_pro_rounded_medium, FontWeight.Medium),
        Font(R.font.sf_pro_rounded_bold, FontWeight.Bold)
    )
}