package com.roliveira.spendaholic.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _navigateToScreen = MutableLiveData<String?>()
    val navigateToScreen: LiveData<String?> = _navigateToScreen

    fun navigateTo(screenRoute: String) {
        _navigateToScreen.value = screenRoute
    }

    fun navigationDone() {
        _navigateToScreen.value = null
    }
}