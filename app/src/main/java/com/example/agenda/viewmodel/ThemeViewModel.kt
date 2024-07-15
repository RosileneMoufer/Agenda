package com.example.agenda.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agenda.backend.entity.TaskEntity
import com.example.agenda.backend.repository.toTaskModel
import com.example.agenda.state.ThemeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ThemeViewModel: ViewModel() {
    private val _uiState: MutableStateFlow<ThemeUiState> =
        MutableStateFlow(ThemeUiState())
    val uiState = _uiState.asStateFlow()

    fun changeThemeOfApp(darkTheme: Boolean) {
        _uiState.update {
            it.copy(
                isDarkTheme = darkTheme
            )}
    }
}