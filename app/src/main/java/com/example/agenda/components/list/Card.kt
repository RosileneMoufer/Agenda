package com.example.agenda.components.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agenda.components.button.CardButton
import com.example.agenda.constants.ItemsMenu
import com.example.agenda.model.TaskModel
import com.example.agenda.ui.theme.ButtonDeleteTask
import com.example.agenda.ui.theme.ButtonInactive
import com.example.agenda.ui.theme.Primary
import com.example.agenda.ui.theme.Secondary
import com.example.agenda.ui.theme.StrokeForm
import com.example.agenda.viewmodel.TaskFormViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Card(task: TaskModel, navController: NavController) {
    var isCardExpanded by remember { mutableStateOf(false) }
    val viewModel = koinViewModel<TaskFormViewModel>()


    if (isCardExpanded) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    border = BorderStroke(1.dp, StrokeForm),
                    shape = RoundedCornerShape(6)
                )
                .padding(vertical = 16.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                IconButton(onClick = { isCardExpanded = !isCardExpanded }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowUp,
                        contentDescription = "show less",
                        tint = Primary
                    )
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Título",
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W600)
                )
                Text(
                    task.title,
                    style = TextStyle(fontSize = 14.sp)
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Descrição",
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W600)
                )
                Text(
                    task.description,
                    style = TextStyle(fontSize = 14.sp)
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Status",
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W600)
                )
                StatusComponent(task.status)
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Data",
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W600)
                )
                Text(
                    task.date,
                    style = TextStyle(fontSize = 14.sp)
                )
            }/*
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Hora",
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W600)
                )
                Text(
                    "00:00",
                    style = TextStyle(fontSize = 14.sp)
                )
            }*/
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.End),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CardButton("Alterar", ButtonInactive, Primary, 14.sp) {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "task",
                        value = task
                    )

                    navController.navigate(ItemsMenu.UPDATE_TASK.name)
                }
                CardButton("Deletar", ButtonDeleteTask, Secondary, 14.sp) {
                    viewModel.delete(task)
                }
            }
        }
    } else {
        Row(
            Modifier
                .fillMaxWidth()
                .border(
                    border = BorderStroke(1.dp, StrokeForm),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(top = 12.dp, bottom = 12.dp, start = 12.dp, end = 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(task.title)
            IconButton(onClick = { isCardExpanded = !isCardExpanded }) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "show more",
                    tint = Primary
                )
            }
        }
    }
}

@Composable
fun StatusComponent(status: String) {
    val result = when (status) {
        "Pendente" -> Box(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .background(Color.LightGray)
                .padding(vertical = 8.dp, horizontal = 24.dp)
        ) {
            Text(text = status.uppercase(), style = TextStyle(color = Secondary))
        }

        "Em progresso" -> Box(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .background(Color.Green)
                .padding(vertical = 8.dp, horizontal = 24.dp)
        ) {
            Text(text = status.uppercase(), style = TextStyle(color = Secondary))
        }

        else -> Box(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .background(Primary)
                .padding(vertical = 8.dp, horizontal = 24.dp)
        ) {
            Text(text = status.uppercase(), style = TextStyle(color = Secondary))
        }
    }

    return result
}