package com.example.agenda.components.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agenda.components.button.StatusButton
import com.example.agenda.constants.TaskStatus
import com.example.agenda.state.TaskFormUiState
import com.example.agenda.ui.theme.ButtonInactive
import com.example.agenda.ui.theme.Primary
import com.example.agenda.ui.theme.Secondary
import com.example.agenda.ui.theme.Title
import com.example.agenda.viewmodel.TaskFormViewModel

@Composable
fun StatusComponent(uiState: TaskFormUiState, formViewModel: TaskFormViewModel) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)) {
        Text(
            "Status", style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.W500
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            for (item in TaskStatus.entries) {
                StatusButton(
                    Modifier.weight(1F),
                    title = item.value,
                    backgroundColor = if (uiState.status == item.value) Primary else ButtonInactive,
                    textColor = if (uiState.status == item.value) Secondary else Title,
                    12.sp
                ) {
                    formViewModel.setStatusValue(item.value)
                }
            }
        }
    }
}