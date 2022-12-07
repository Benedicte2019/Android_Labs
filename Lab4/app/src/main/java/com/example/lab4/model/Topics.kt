package com.example.lab4.model

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lab4.R

@Composable
fun Topics(){
    val articleList = listOf(
        "Money Inflation",
        "Food Security in Rwanda",
        "Job Creation Challenges in Africa",
        "Effective Networking"
    )

    Column(modifier = Modifier.padding(20.dp)
        .verticalScroll(rememberScrollState())) {
        Text(text = stringResource(id = R.string.new_topics), fontWeight = FontWeight.W700)
        articleList.forEach{ article ->
            Card(
                elevation = 20.dp,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .height(60.dp),

            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(text = article)
                }
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }

}