package com.example.agenda.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agenda.components.form.Divisor
import com.example.agenda.components.button.ActionButton
import com.example.agenda.components.form.CalendarComponent
import com.example.agenda.components.form.DateComponent
import com.example.agenda.components.form.DescriptionComponent
import com.example.agenda.components.form.StatusComponent
import com.example.agenda.components.form.TitleComponent
import com.example.agenda.components.menu.TopBarNewTask
import com.example.agenda.state.TaskFormUiState
import com.example.agenda.viewmodel.TaskFormViewModel
import kotlinx.coroutines.launch

@Composable
fun NewTaskScreen(
    navController: NavController,
    formViewModel: TaskFormViewModel,
    uiState: TaskFormUiState
) {
    val scope = rememberCoroutineScope()
    val ctx = LocalContext.current

    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        topBar = {
            TopBarNewTask(
                title = "Criar Task",
                titleColor = MaterialTheme.colorScheme.tertiary,
                navController,
                formViewModel
            )
        },
        bottomBar = {
            ActionButton(
                "Criar",
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.background
            ) {
                scope.launch {
                    if ((uiState.title != "" && uiState.title.isNotBlank())
                        && (uiState.description != "" && uiState.description.isNotBlank())
                        && (uiState.date != "" && uiState.date.isNotBlank())
                    ) {
                        formViewModel.save()
                        navController.popBackStack()
                    } else {
                        Toast.makeText(
                            ctx,
                            "Preencha todos os campos!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        },
    ) { innitPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innitPadding)
                .verticalScroll(rememberScrollState())
        ) {
            TitleComponent(taskFormUiState = uiState)

            Divisor()

            DescriptionComponent(uiState)

            Divisor()

            StatusComponent(uiState, formViewModel)

            Divisor()

            DateComponent(uiState, formViewModel)

            if (formViewModel.isOpenCalendar()) {
                CalendarComponent(formViewModel)
            }
        }
    }
}

