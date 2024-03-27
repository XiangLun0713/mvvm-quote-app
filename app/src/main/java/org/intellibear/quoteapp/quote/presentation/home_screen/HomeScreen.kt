package org.intellibear.quoteapp.quote.presentation.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.intellibear.quoteapp.global.components.LoadingDialog
import org.intellibear.quoteapp.global.components.MyTopAppBar

@Composable
internal fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreenContent(state = state) { viewModel.getQuote() }
}

@Composable
fun HomeScreenContent(
    state: HomeViewState,
    onRefreshButtonClicked: () -> Unit
) {
    LoadingDialog(isLoading = state.isLoading)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopAppBar(title = "Quotes")
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onRefreshButtonClicked) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
            }
        }
    ) { paddingValues ->
        if (state.quote != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
            ) {
                // row of tags
                LazyRow(
                    modifier = Modifier.padding(0.dp),
                    contentPadding = PaddingValues(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.quote.tags) { tag ->
                        Card(
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = "# $tag",
                                fontFamily = FontFamily.Serif
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                // quote section
                Row(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "❝",
                        style = MaterialTheme.typography.displaySmall,
                        fontStyle = FontStyle.Italic,
                        fontFamily = FontFamily.Serif
                    )
                    Text(
                        text = state.quote.content,
                        style = MaterialTheme.typography.displaySmall,
                        fontStyle = FontStyle.Italic,
                        fontFamily = FontFamily.Serif
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = "— ${state.quote.author}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = FontFamily.Serif
                )
            }
        }
    }
}
