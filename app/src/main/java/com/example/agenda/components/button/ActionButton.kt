package com.example.agenda.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ActionButton(text: String, backgroundColor: Color, textColor: Color, onClick: () -> Unit) {
    BottomAppBar(
        containerColor = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .height(60.dp)
                .background(backgroundColor)
                .clickable { onClick() },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                style = TextStyle(color = textColor, fontSize = 16.sp, fontWeight = FontWeight.W600)
            )
        }
    }
}