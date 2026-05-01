package com.example.quotesapp.Screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.quotesapp.models.Quote

@Composable
fun QuoteList(data:ArrayList<Quote> , onclick: (Quote) -> Unit  ){

    LazyColumn( content = {
        items(data.size){ quote ->
            QuoteListItem(quote = data[quote]){onclick((data[quote]))}
        }
    })

}