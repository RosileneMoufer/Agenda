package com.example.agenda.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agenda.R
import com.example.agenda.components.button.ActionButton
import com.example.agenda.components.list.Card
import com.example.agenda.components.menu.HomeSubMenu
import com.example.agenda.components.menu.TopBarHome
import com.example.agenda.constants.ItemsMenu
import com.example.agenda.state.TasksListUiState
import com.example.agenda.state.ThemeUiState
import com.example.agenda.viewmodel.ThemeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    uiState: TasksListUiState,
    themeViewModel: ThemeViewModel,
    themeUiState: ThemeUiState
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { 3 })

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp), // MaterialTheme.colors.secondary
        topBar = {
            TopBarHome(
                title = "Projeto",
                titleColor = MaterialTheme.colorScheme.tertiary,
                themeViewModel,
                themeUiState,
                navController
            )
        },
        bottomBar = {
            ActionButton(
                "Criar Tasks",
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.background
            ) {
                navController.navigate(ItemsMenu.NEW_TASK.name)
            }
        },
    ) { innitPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(
                    top = innitPadding.calculateTopPadding(),
                    bottom = innitPadding.calculateBottomPadding()
                ),
            verticalArrangement = Arrangement.Center
        ) {
            HomeSubMenu(
                onClickButtonLeft = {
                    scope.launch {
                        pagerState.animateScrollToPage(0)
                    }
                },
                onClickButtonCenter = {
                    scope.launch {
                        pagerState.animateScrollToPage(1)
                    }
                },
                onClickButtonRight = {
                    scope.launch {
                        pagerState.animateScrollToPage(2)
                    }
                },
                currentItemSubMenuActive = pagerState.currentPage
            )
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(vertical = 16.dp)
            ) { page ->
                ShowTasks(page, uiState, navController)
            }
        }
    }
}

@Composable
fun ShowTasks(page: Int, uiState: TasksListUiState, navController: NavController) {
    when (page) {
        0 -> {
            if (uiState.pendingTasks.isEmpty()) {
                WhenDontHaveNothingToShow("pendentes")
            } else {
                LazyColumn(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    itemsIndexed(uiState.pendingTasks) { _, task ->
                        Card(task, navController)
                    }
                }
            }
        }

        1 -> {
            if (uiState.inProgressTasks.isEmpty()) {
                WhenDontHaveNothingToShow("em progresso")
            } else {
                LazyColumn(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    itemsIndexed(uiState.inProgressTasks) { _, task ->
                        Card(task, navController)
                    }
                }
            }
        }

        2 -> {
            if (uiState.finishedTasks.isEmpty()) {
                WhenDontHaveNothingToShow("terminados")
            } else {
                LazyColumn(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    itemsIndexed(uiState.finishedTasks) { _, task ->
                        Card(task, navController)
                    }
                }
            }
        }

        else -> {
            // algo deu errado...
            Text(
                text = "Algo deu errado...", style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W900,
                    lineHeight = 22.sp,
                    letterSpacing = 1.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

@Composable
fun WhenDontHaveNothingToShow(taskStatus: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(
            12.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.empty),
            contentDescription = "no data",
            modifier = Modifier
                .width(120.dp)
                .height(120.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Nada aqui, por agora.", style = TextStyle(
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 18.sp,
                fontWeight = FontWeight.W900,
                lineHeight = 22.sp,
                letterSpacing = 1.sp,
                textAlign = TextAlign.Center
            )
        )
        Text(
            "É aqui que você encontrará seus projetos $taskStatus.",
            style = TextStyle(
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.outlineVariant
            )
        )
    }
}