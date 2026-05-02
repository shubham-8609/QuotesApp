package com.example.quotesapp.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.quotesapp.models.Quote


@Composable
fun QuoteListScreen(data: ArrayList<Quote> , onclick : (quote:Quote) -> Unit){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Quotes App",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 23.dp , horizontal = 8.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.headlineLarge
        )
        QuoteList(data = data , onclick)
    }



}