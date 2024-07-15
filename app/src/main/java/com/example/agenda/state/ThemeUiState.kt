package com.example.agenda.state

data class ThemeUiState (
    val isDarkTheme: Boolean = false,
    val onThemeChange: (String) -> Unit = {},
)