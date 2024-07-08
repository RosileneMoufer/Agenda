package com.example.agenda.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.agenda.constants.ItemsSubMenu

class HomeViewModel: ViewModel() {
    private val _currentItemSubMenuActive = mutableStateOf(ItemsSubMenu.PENDING.name)

    var currentItemSubMenuActive = _currentItemSubMenuActive
}