package com.example.quotesapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.quotesapp.models.Quote



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun QuoteListScreen(
    data: ArrayList<Quote>,
    states : Pair<Boolean , PullRefreshState>,
    onclick: (quote: Quote) -> Unit,
    isDark:Boolean,
    onToggleTheme: () -> Unit
) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(0.dp , 20.dp , 0.dp ,0.dp )
        ) {
            Text(
                text = "Quotes App",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(vertical = 23.dp, horizontal = 8.dp)
                    .fillMaxWidth()
                    .weight(1f),
                style = MaterialTheme.typography.headlineLarge
            )

            Image(
                imageVector = if (isDark) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                contentDescription = "Dark Mode Icon",
                modifier = Modifier
                    .padding(0.dp, 0.dp, 25.dp, 0.dp)
                    .clickable {
                        onToggleTheme()

                    }
                    .size(28.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)



            )

        }
        Box() {
            PullRefreshIndicator(
                refreshing = states.first,
                state = states.second,
                modifier = Modifier.align(alignment = Alignment.TopCenter)
                    .zIndex(1f)
            )
            QuoteList(data = data, onclick)

        }
    }


}