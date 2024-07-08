package com.example.agenda.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.agenda.constants.ItemsSubMenu
import com.example.agenda.constants.itemsSubMenu

class HomeViewModel: ViewModel() {
    private val _currentItemSubMenuActive = mutableStateOf(itemsSubMenu[0])

    var currentItemSubMenuActive = _currentItemSubMenuActive
}