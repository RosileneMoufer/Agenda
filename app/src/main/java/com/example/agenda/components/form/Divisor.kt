package com.example.agenda.components.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agenda.ui.theme.StrokeForm

@Composable
fun Divisor() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .background(StrokeForm)
            .height(1.dp)
    )
}