package com.example.quotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.quotesapp.Screens.QuoteDetail
import com.example.quotesapp.Screens.QuoteListScreen
import com.example.quotesapp.ui.theme.DataManager
import com.example.quotesapp.ui.theme.QuotesAppTheme
import kotlinx.coroutines.delay

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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun App() {
    val isDark = remember { mutableStateOf(false) }

    QuotesAppTheme(isDark.value) {

        if (DataManager.isDataLoaded.value) {
            if (DataManager.currentPage.value == Pages.LISTING) {

                // UI
                    var refreshing by remember { mutableStateOf(false) }

                    val pullRefreshState = rememberPullRefreshState(
                        refreshing  = refreshing ,
                        onRefresh = {
                            refreshing = true
                        }
                    )

                    // Using LaunchedEffect to handle  some business logic
                    LaunchedEffect(refreshing) {
                        if(refreshing){
                            DataManager.shuffleData()
                            delay(800)
                            refreshing = false
                        }
                    }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .pullRefresh(pullRefreshState),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    QuoteListScreen(data = DataManager.data , refreshing to pullRefreshState, onclick = {
                        DataManager.switchPages(it)
                    }, isDark.value) {
                        isDark.value = !isDark.value
                    }



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