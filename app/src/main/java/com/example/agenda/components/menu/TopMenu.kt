package com.example.agenda.components.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agenda.ui.theme.Primary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Top(title: String, titleColor: Color,) {
    CenterAlignedTopAppBar(
        title = { Text(text = title, style = TextStyle(color = titleColor)) },

    )
}

@Composable
fun TopMenuHome(
    title: String,
    iconButtonRight: ImageVector,
    titleColor: Color,
    backgroundColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(0.dp, 24.dp),
        //horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = TextStyle(
                color = titleColor,
                fontWeight = FontWeight.W600,
                fontSize = 24.sp,
            )
        )
        Icon(imageVector = iconButtonRight, contentDescription = "menu", tint = Primary)
    }
}


@Composable
fun TopMenu(
    title: String,
    titleButtonLeft: String,
    actionButtonLeft:()->Unit = {},
    titleButtonRight: String,
    actionButtonRight:()->Unit = {},
    actionButtonColor: Color,
    titleColor: Color,
    backgroundColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(0.dp, 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = titleButtonLeft,
            style = TextStyle(
                color = actionButtonColor,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
            ),
            modifier = Modifier.clickable {  actionButtonLeft() }
        )
        Text(
            text = title,
            style = TextStyle(
                color = titleColor,
                fontWeight = FontWeight.W600,
                fontSize = 30.sp,
            )
        )
        Text(
            text = titleButtonRight,
            style = TextStyle(
                color = actionButtonColor,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
            ),
            modifier = Modifier.clickable { actionButtonRight() }
        )
    }
}