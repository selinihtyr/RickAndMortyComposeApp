package com.selin.rickandmortycomposeapp.ui.theme.Character

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.selin.rickandmortycomposeapp.ui.theme.Episode.EpisodeViewModel

@Composable
fun EpisodesFromDetailScreen(
    id: Int,
    viewModel: EpisodeViewModel = hiltViewModel()
) {
    val list = viewModel.list.observeAsState(emptyList())

    LaunchedEffect(key1 = true) {
        viewModel.getEpisodesById(id)
    }

    LazyColumn(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            count = list.value.size,
            itemContent = {
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Row {
                        Text(
                            text = list.value[it].episode,
                            modifier = Modifier.padding(8.dp)
                        )
                        Text(
                            text = list.value[it].name,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        )
    }
}