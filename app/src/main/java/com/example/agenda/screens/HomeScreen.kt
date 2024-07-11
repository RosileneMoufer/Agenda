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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.example.agenda.constants.itemsSubMenu
import com.example.agenda.state.TasksListUiState
import com.example.agenda.ui.theme.Primary
import com.example.agenda.ui.theme.Secondary
import com.example.agenda.ui.theme.SubTitle
import com.example.agenda.ui.theme.Title
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavController, uiState: TasksListUiState) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { 3 })

    var subMenuState by remember { mutableStateOf(itemsSubMenu[pagerState.currentPage]) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Secondary)
            .padding(16.dp),
        topBar = {
            TopBarHome(
                title = "Projeto",
                titleColor = Title
            )
        },
        bottomBar = {
            ActionButton("Criar Tasks", Primary, Secondary) {
                navController.navigate(ItemsMenu.NEW_TASK.name)
            }
        },
    ) { innitPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Secondary)
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
                subMenuState = itemsSubMenu[pagerState.currentPage]
                ShowTasks(page, uiState, navController)
            }
        }
    }
}

@Composable
fun ShowTasks(page: Int, uiState:TasksListUiState, navController: NavController) {
    val myTasks = intArrayOf()

    when (page) {
        0 -> {
            if (uiState.tasks.isEmpty()) {
                WhenDontHaveNothingToShow("pendentes")
            } else {
                LazyColumn(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) { itemsIndexed(uiState.tasks) { _, task ->
                    Card(task, navController)
                }}
            }
        }

        1 -> {
            // fazer a requisição no banco
            // verificar se o valor de retorno é vazio
            // chamar o componente

            if (myTasks.isEmpty()) {
                WhenDontHaveNothingToShow("em progresso")
            } else {
                //Card(task)
            }
        }

        2 -> {
            // fazer a requisição no banco
            // verificar se o valor de retorno é vazio
            // chamar o componente

            if (myTasks.isEmpty()) {
                WhenDontHaveNothingToShow("terminados")
            } else {
                //Card(task)
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
                fontSize = 18.sp,
                fontWeight = FontWeight.W900,
                lineHeight = 22.sp,
                letterSpacing = 1.sp,
                textAlign = TextAlign.Center
            )
        )
        Text(
            "É aqui que você encontrará seus projetos $taskStatus.",
            style = TextStyle(textAlign = TextAlign.Center, color = SubTitle)
        )
    }
}