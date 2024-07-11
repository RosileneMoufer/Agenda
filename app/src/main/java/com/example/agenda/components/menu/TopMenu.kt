package com.example.agenda.components.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agenda.ui.theme.Primary
import com.example.agenda.ui.theme.Secondary
import com.example.agenda.viewmodel.FormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarHome(title: String, titleColor: Color) {
    CenterAlignedTopAppBar(
        modifier = Modifier.padding(bottom = 20.dp),
        colors = TopAppBarDefaults.topAppBarColors(Secondary),
        title = {
            Text(
                text = title,
                style = TextStyle(
                    color = titleColor,
                    fontWeight = FontWeight.W700,
                    fontSize = 22.sp,
                )
            )
        },
        actions = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "search",
                tint = Primary
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNewTask(
    title: String,
    titleColor: Color,
    navController: NavController,
    formViewModel: FormViewModel
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.padding(bottom = 20.dp),
        colors = TopAppBarDefaults.topAppBarColors(Secondary),
        navigationIcon = {
            ActionTextButton("Cancelar") { navController.popBackStack() }
        },
        title = {
            Text(
                text = title,
                style = TextStyle(
                    color = titleColor,
                    fontWeight = FontWeight.W700,
                    fontSize = 22.sp,
                )
            )
        },
        actions = {
            ActionTextButton("Limpar") {
                formViewModel.cleanForm()
            }
        }
    )
}

@Composable
fun ActionTextButton(textButton: String, onClick: () -> Unit) {
    Text(
        modifier = Modifier.clickable { onClick() },
        text = textButton,
        style = TextStyle(
            color = Primary,
            fontSize = 14.sp,
            fontWeight = FontWeight.W700
        )
    )
}