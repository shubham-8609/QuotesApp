package com.example.quotesapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.quotesapp.models.Quote



@Composable
fun QuoteListScreen(
    data: ArrayList<Quote>,
    onclick: (quote: Quote) -> Unit,
    onToggleTheme: () -> Unit
) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Quotes App",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 23.dp, horizontal = 8.dp)
                    .fillMaxWidth()
                    .weight(1f),
                style = MaterialTheme.typography.headlineLarge
            )
            Image(
                imageVector = Icons.Filled.DarkMode,
                contentDescription = "Dark Mode Icon",
                modifier = Modifier
                    .clickable {
                        onToggleTheme()
                    }
                    .padding(0.dp, 0.dp, 25.dp, 0.dp)
                    .size(28.dp)
                    .background(MaterialTheme.colorScheme.background),

            )

        }
        QuoteList(data = data, onclick)

    }


}