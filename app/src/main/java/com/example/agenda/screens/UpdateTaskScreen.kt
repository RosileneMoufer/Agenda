package com.example.agenda.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.agenda.components.form.Divisor
import com.example.agenda.components.button.ActionButton
import com.example.agenda.components.form.CalendarComponent
import com.example.agenda.components.form.DateComponent
import com.example.agenda.components.form.DescriptionComponent
import com.example.agenda.components.form.StatusComponent
import com.example.agenda.components.form.TitleComponent
import com.example.agenda.components.menu.TopBarNewTask
import com.example.agenda.model.TaskModel
import com.example.agenda.state.TaskFormUiState
import com.example.agenda.ui.theme.AgendaTheme
import com.example.agenda.viewmodel.TaskFormViewModel
import com.example.agenda.viewmodel.ThemeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun UpdateTaskScreen(
    task: TaskModel,
    taskFormViewModel: TaskFormViewModel,
    uiState: TaskFormUiState,
    navController: NavHostController
) {

    val scope = rememberCoroutineScope()
    val ctx = LocalContext.current


    LaunchedEffect(task.id) {
        taskFormViewModel.setTheValueTaskToShowOnUpdateScreen(task)
    }

    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        topBar = {
            TopBarNewTask(
                title = "Alterar Task",
                titleColor = MaterialTheme.colorScheme.tertiary,
                navController,
                taskFormViewModel
            )
        },
        bottomBar = {
            ActionButton(
                "Alterar",
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.background
            ) {
                scope.launch {
                    if ((uiState.title != "" && uiState.title.isNotBlank())
                        && (uiState.description != "" && uiState.description.isNotBlank())
                        && (uiState.date != "" && uiState.date.isNotBlank())
                    ) {
                        taskFormViewModel.update(task)
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
            TitleComponent(uiState)

            Divisor()

            DescriptionComponent(uiState = uiState)

            Divisor()

            StatusComponent(uiState, taskFormViewModel)

            Divisor()

            DateComponent(uiState = uiState, formViewModel = taskFormViewModel)

            if (taskFormViewModel.isOpenCalendar()) {
                CalendarComponent(taskFormViewModel)
            }
        }
    }
}
