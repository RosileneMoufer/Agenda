package com.example.agenda.screens

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.agenda.components.list.Card
import com.example.agenda.components.menu.TopBarSearchScreen
import com.example.agenda.state.SearchUiState
import com.example.agenda.state.TasksListUiState
import com.example.agenda.ui.theme.AgendaTheme
import com.example.agenda.viewmodel.SearchViewModel
import com.example.agenda.viewmodel.TasksListViewModel
import com.example.agenda.viewmodel.ThemeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    navController: NavController
) {
    val searchViewModel = koinViewModel<SearchViewModel>()
    val searchUiState by searchViewModel.uiState.collectAsState(SearchUiState())

    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        topBar = {
            TopBarSearchScreen(
                navController = navController,
                searchViewModel = searchViewModel,
                searchStateUiState = searchUiState
            )
        }
    ) { innitPadding ->
        if (searchUiState.tasks.isEmpty()) {
            NothingToShow()
        } else {
            LazyColumn(
                Modifier
                    .padding(innitPadding)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(searchUiState.tasks) { _, task ->
                    Card(task, navController)
                }
            }
        }
    }

}

@Composable
fun NothingToShow() {
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
            text = "Nada para exibir.", style = TextStyle(
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 18.sp,
                fontWeight = FontWeight.W900,
                lineHeight = 22.sp,
                letterSpacing = 1.sp,
                textAlign = TextAlign.Center
            )
        )
    }
}