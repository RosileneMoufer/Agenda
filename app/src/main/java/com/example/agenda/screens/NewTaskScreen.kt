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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agenda.components.Divisor
import com.example.agenda.components.button.ActionButton
import com.example.agenda.components.button.StatusButton
import com.example.agenda.components.menu.TopBarNewTask
import com.example.agenda.constants.TaskStatus
import com.example.agenda.state.TaskFormUiState
import com.example.agenda.ui.theme.ButtonInactive
import com.example.agenda.ui.theme.Primary
import com.example.agenda.ui.theme.Secondary
import com.example.agenda.ui.theme.StrokeForm
import com.example.agenda.ui.theme.Title
import com.example.agenda.viewmodel.FormViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewTaskScreen(navController: NavController, viewModel2: FormViewModel, uiState2: TaskFormUiState) {
    val scope = rememberCoroutineScope()

    val viewModel = koinViewModel<FormViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier
            .background(Secondary)
            .padding(16.dp),
        topBar = {
            TopBarNewTask(
                title = "Criar Task",
                titleColor = Title,
                navController,
                viewModel
            )
        },
        bottomBar = {
            ActionButton("Criar", Primary, Secondary) {
                scope.launch {
                    viewModel.save()

                    navController.popBackStack()
                }
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
            TextField(
                value = uiState.title,
                onValueChange = uiState.onTitleChange,
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
                    focusedBorderColor = StrokeForm,
                    focusedLabelColor = Title,
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Divisor()

            DescriptionComponent(uiState)

            Divisor()

            StatusComponent(uiState, viewModel)

            Divisor()

            DateComponent(uiState)
        }
    }
}

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

@Composable
fun StatusComponent(uiState: TaskFormUiState, viewModel: FormViewModel) {
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
                    //uiState.status = item.value
                    viewModel.statusForm(item.value)
                }
            }
        }
    }
}

@Composable
fun DateComponent(uiState: TaskFormUiState) {
    TextField(
        value = uiState.date,
        onValueChange = uiState.onDateChange,
        label = {
            Text(
                text = "Data",
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