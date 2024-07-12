package com.example.agenda.components.form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.agenda.state.TaskFormUiState
import com.example.agenda.ui.theme.Secondary
import com.example.agenda.ui.theme.Title

@Composable
fun TitleComponent(taskFormUiState: TaskFormUiState) {
    TextField(
        value = taskFormUiState.title,
        onValueChange = taskFormUiState.onTitleChange,
        label = {
            Text(
                text = "Título",
                fontSize = 16.sp,
                fontWeight = FontWeight.W500
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Secondary,
            unfocusedBorderColor = Secondary,
            unfocusedTextColor = Title,
            focusedContainerColor = Secondary,
            focusedBorderColor = Color.Transparent,
            focusedLabelColor = Title,
        ),
        modifier = Modifier.fillMaxWidth()
    )
}