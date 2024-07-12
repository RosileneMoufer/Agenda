package com.example.agenda.components.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agenda.state.TaskFormUiState
import com.example.agenda.ui.theme.Primary
import com.example.agenda.viewmodel.TaskFormViewModel

@Composable
fun DateComponent(uiState: TaskFormUiState, formViewModel: TaskFormViewModel) {
    Row(
        Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Data", style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.W500
            )
        )
        Text(
            text = uiState.date,
        )
        IconButton(onClick = { formViewModel.isOpenCalendar.value = true }) {
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = "Calendar",
                tint = Primary
            )
        }
    }
}