package com.example.agenda.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.agenda.R
import com.example.agenda.components.button.ActionButton
import com.example.agenda.components.menu.BottomMenu
import com.example.agenda.components.menu.HomeSubMenu
import com.example.agenda.components.menu.TopMenu
import com.example.agenda.components.menu.TopMenuHome
import com.example.agenda.constants.itemsSubMenu
import com.example.agenda.ui.theme.Primary
import com.example.agenda.ui.theme.Secondary
import com.example.agenda.ui.theme.SubTitle
import com.example.agenda.ui.theme.Title
import com.example.agenda.viewmodel.BottomMenuViewModel
import com.example.agenda.viewmodel.HomeViewModel

@Composable
fun HomeScreen(bottomMenuViewModel: BottomMenuViewModel, navController: NavController) {
    val homeViewModel = viewModel<HomeViewModel>()

    Scaffold(
        modifier = Modifier
            .background(Secondary)
            .padding(16.dp),
        topBar = {
            TopMenuHome(
                title = "Projeto",
                Icons.Filled.Search,
                titleColor = Title,
                backgroundColor = Secondary
            )
        },
        bottomBar = {
            ActionButton("Criar Tasks", Primary, Secondary)
        },
    ) { innitPadding ->
        Column(
            modifier = Modifier
                .background(Secondary)
                .padding(innitPadding),
            verticalArrangement = Arrangement.Center
        ) {
            HomeSubMenu(
                onClickButtonLeft = { homeViewModel.currentItemSubMenuActive.value = itemsSubMenu[0] },
                onClickButtonCenter = { homeViewModel.currentItemSubMenuActive.value = itemsSubMenu[1] },
                onClickButtonRight = { homeViewModel.currentItemSubMenuActive.value = itemsSubMenu[2] },
                currentItemSubMenuActive = homeViewModel.currentItemSubMenuActive.value
            )
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
                        .width(100.dp)
                        .height(100.dp)
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
                    "É aqui que você encontrará seus projetos pendentes.",
                    style = TextStyle(textAlign = TextAlign.Center)
                )
            }
        }
    }
}