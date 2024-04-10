package com.roliveira.spendaholic.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roliveira.spendaholic.ui.screens.settings.categories.SettingCategories
import com.roliveira.spendaholic.ui.screens.settings.currency.SettingCurrency
import com.roliveira.spendaholic.ui.theme.SpendaholicTheme

@Composable
fun Settings() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SettingsHeader()

        Spacer(modifier = Modifier.height(18.dp))

        SettingCurrency()

        Spacer(modifier = Modifier.height(8.dp))

        SettingCategories()
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreviewPreview() {
    SpendaholicTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Settings()
        }
    }
}