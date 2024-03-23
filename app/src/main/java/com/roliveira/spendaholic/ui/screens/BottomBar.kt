package com.roliveira.spendaholic.ui.screens

import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.ui.MainViewModel
import com.roliveira.spendaholic.ui.screensBottomNavigation
import com.roliveira.spendaholic.ui.theme.SpendaholicTheme

@Composable
fun BottomBar(viewModel: MainViewModel = MainViewModel(Application()), currentRoute: String?) {
    BottomAppBar(
        elevation = 16.dp,
        backgroundColor = Color.White
    ) {
        screensBottomNavigation.forEach {
            item ->
                val selected = currentRoute == item.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = { viewModel.navigateTo(item.route) },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title,
                            modifier = Modifier.size(26.dp),
                            tint = if (selected) colorResource(id = R.color.heavy_blue) else colorResource(id = R.color.grey)
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            color = if (selected) colorResource(id = R.color.black) else colorResource(id = R.color.grey)
                        )
                    },
                    modifier = Modifier.padding(bottom = 4.dp, top = 8.dp)
                )
        }
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
            BottomBar(currentRoute = "dashBoard")
        }
    }
}