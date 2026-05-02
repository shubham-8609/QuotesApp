package com.example.quotesapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.ModifierLocalConsumer
import androidx.compose.ui.platform.LocalView
import com.example.quotesapp.Screens.QuoteDetail
import com.example.quotesapp.Screens.QuoteListScreen
import com.example.quotesapp.models.Quote
import com.example.quotesapp.ui.theme.DataManager
import com.example.quotesapp.ui.theme.QuotesAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(Unit) {
                DataManager.loadAssetsFromFile(applicationContext)
            }
            App()

        }
    }


}

@Composable
fun App() {
    val isDark = remember { mutableStateOf(false) }

    QuotesAppTheme(isDark.value) {

        if (DataManager.isDataLoaded.value) {
            if (DataManager.currentPage.value == Pages.LISTING) {
                QuoteListScreen(data = DataManager.data, onclick = {
                    DataManager.switchPages(it)
                }) {
                    isDark.value = !isDark.value
                }
            } else DataManager.currentQuote?.let { QuoteDetail(it) }

        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Loading...",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}


enum class Pages {
    LISTING,
    DETAIL
}