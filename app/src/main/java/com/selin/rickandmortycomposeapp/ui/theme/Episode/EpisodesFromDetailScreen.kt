package com.selin.rickandmortycomposeapp.ui.theme.Episode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.selin.rickandmortycomposeapp.data.retrofit.response.EpisodeResponseList

@Composable
fun EpisodesFromDetailScreen(
    ids : List<Int>,
    viewModel: EpisodeViewModel = hiltViewModel()) {
    val episode = remember { mutableStateOf<List<EpisodeResponseList>?>(null) }


    LaunchedEffect(ids) {
        val response = viewModel.getEpisodesIds(ids)
        if (response.isSuccessful) {
            // Assuming the API returns a single EpisodeResponseList, not a list
            episode.value = listOf(response.body()!!)
        } else {
            // Handle the error case here
        }
    }

    LazyColumn(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            count = episode.value?.size ?: 0,
            itemContent = { index ->
                val currentEpisode = episode.value?.get(index)
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Row {
                        Text(
                            text = currentEpisode?.episode.toString(),
                            modifier = Modifier.padding(8.dp)
                        )
                        Text(
                            text = currentEpisode?.name.toString(),
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        )
    }
}