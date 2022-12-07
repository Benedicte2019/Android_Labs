package com.example.lab4.model

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Favorite(sampleArticles: List<Article>){
    Column(modifier = Modifier.padding(20.dp)) {
        Card (
            elevation = 20.dp,
        )
        {
            Column(modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()) {
                Text(text = sampleArticles[0].title, fontWeight = FontWeight.W700, color = MaterialTheme.colors.primaryVariant)
                Text(text = sampleArticles[0].description)
            }
        }
    }

}