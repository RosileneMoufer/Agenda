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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.agenda.R
import com.example.agenda.components.menu.BottomMenu
import com.example.agenda.components.menu.HomeSubMenu
import com.example.agenda.ui.theme.Secondary
import com.example.agenda.viewmodel.BottomMenuViewModel
import com.example.agenda.viewmodel.HomeViewModel

@Composable
fun HomeScreen(bottomMenuViewModel: BottomMenuViewModel, navController: NavController) {
    val homeViewModel = viewModel<HomeViewModel>()

    Scaffold(
        modifier = Modifier.background(Secondary),
        bottomBar = {
            BottomMenu(
                bottomViewModel = bottomMenuViewModel,
                navController = navController
            )
        }
    ) { innitPadding ->
        Column(
            modifier = Modifier.padding(innitPadding),
            verticalArrangement = Arrangement.Center
        ) {
            HomeSubMenu(
                onClickButtonLeft = { /*TODO*/ },
                onClickButtonCenter = { /*TODO*/ },
                onClickButtonRight = { /*TODO*/ },
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
                        .width(200.dp)
                        .height(200.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Nada aqui, por agora.", style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W900,
                        lineHeight = 22.sp,
                        letterSpacing = 2.sp,
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