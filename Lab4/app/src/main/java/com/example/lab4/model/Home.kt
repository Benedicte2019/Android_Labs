package com.example.lab4.model

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab4.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home (sampleArticles: List<Article>){

    Column(modifier = Modifier
        .padding(20.dp)
        .verticalScroll(rememberScrollState())) {
        Text(text = stringResource(id = R.string.all_article), fontWeight = FontWeight.W700, fontSize = 20.sp)
        Spacer(modifier = Modifier.size(20.dp))
        
        sampleArticles.forEach{ article ->  
            Card(elevation = 20.dp,
//            modifier = Modifier.combinedClickable (
//                onClick = {}
//                    )
            ) {
                Column(modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()) {
                    Text(text = article.title, fontWeight = FontWeight.W700, color = MaterialTheme.colors.primaryVariant)
                    Text(text = article.description)
                }

            }
            Spacer(modifier = Modifier.size(20.dp))
        }
    }
}