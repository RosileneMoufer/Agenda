package com.example.agenda.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.agenda.backend.data.Task
import com.example.agenda.components.Divisor
import com.example.agenda.components.button.ActionButton
import com.example.agenda.components.button.StatusButton
import com.example.agenda.components.menu.TopBarNewTask
import com.example.agenda.constants.ItemsMenu
import com.example.agenda.constants.TaskStatus
import com.example.agenda.ui.theme.ButtonInactive
import com.example.agenda.ui.theme.Primary
import com.example.agenda.ui.theme.Secondary
import com.example.agenda.ui.theme.StrokeForm
import com.example.agenda.ui.theme.Title
import com.example.agenda.viewmodel.FormViewModel

@Composable
fun UpdateTaskScreen(task: Task, navController: NavController) {
    val formViewModel = viewModel<FormViewModel>()

    Scaffold(
        modifier = Modifier
            .background(Secondary)
            .padding(16.dp),
        topBar = {
            TopBarNewTask(
                title = "Alterando Task",
                titleColor = Title,
                navController
            )
        },
        bottomBar = {
            ActionButton("Alterar", Primary, Secondary) {
                navController.navigate(ItemsMenu.NEW_TASK.name)
            }
        },
    ) { innitPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Secondary)
                .padding(innitPadding)
                .verticalScroll(rememberScrollState())
        ) {
            TaskComponent(task, formViewModel)

            Divisor()

            DescriptionComponent(task)

            Divisor()

            StatusComponent(task)

            Divisor()

            HourComponent(task)
        }
    }
}

@Composable
fun TaskComponent(task: Task, formViewModel: FormViewModel) {
    TextField(
        value = formViewModel.title.value,
        onValueChange = { formViewModel.title.value = it },
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
            unfocusedTextColor = Secondary,
            focusedContainerColor = Secondary,
            focusedBorderColor = StrokeForm,
            focusedLabelColor = Title,
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun DescriptionComponent(task: Task) {
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
        Text(
            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
            modifier = Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp, bottom = 24.dp)
        )
    }
}

@Composable
fun StatusComponent(task: Task) {
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
                    backgroundColor = ButtonInactive,
                    textColor = Title,
                    12.sp
                ) {
                    // TODO
                }
            }
        }
    }
}

@Composable
fun HourComponent(task: Task) {
    var isExpanded by remember { mutableStateOf(false) }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Hora", style = TextStyle(
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
        // TODO
    }
}