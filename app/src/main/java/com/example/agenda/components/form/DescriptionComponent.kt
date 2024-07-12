package com.example.agenda.components.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agenda.state.TaskFormUiState
import com.example.agenda.ui.theme.Primary
import com.example.agenda.ui.theme.Secondary
import com.example.agenda.ui.theme.Title

@Composable
fun DescriptionComponent(uiState: TaskFormUiState) {
    var isExpanded by remember { mutableStateOf(false) }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Descrição", style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.W500
            )
        )
        IconButton(onClick = { isExpanded = !isExpanded }) {
            Icon(
                imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = "show/hide",
                tint = Primary
            )
        }
    }

    if (isExpanded) {
        TextField(
            value = uiState.description,
            onValueChange = uiState.onDescriptionChange,
            maxLines = 5,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Secondary,
                unfocusedBorderColor = Color.Transparent,
                unfocusedTextColor = Title,
                focusedContainerColor = Secondary,
                focusedBorderColor = Color.Transparent,
                focusedLabelColor = Title,
                cursorColor = Primary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 12.dp,
                    start = 12.dp,
                    end = 12.dp,
                    bottom = 24.dp
                )
        )
    }
}