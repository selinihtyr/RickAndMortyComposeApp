package com.selin.rickandmortycomposeapp.ui.theme.episode

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.selin.rickandmortycomposeapp.data.retrofit.response.EpisodeResponseList

@Composable
fun EpisodesFromDetailScreen(
    navController: NavController,
    ids: List<Int>,
    viewModel: EpisodeViewModel = hiltViewModel()
) {
    val episode = remember { mutableStateOf<List<EpisodeResponseList>?>(null) }


    LaunchedEffect(ids) {
        val response = viewModel.getEpisodesIds(ids)
        if (response.isSuccessful) {
            episode.value = response.body()!!
        }
    }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            count = episode.value?.size ?: 0,
            itemContent = { index ->
                val currentEpisode = episode.value?.get(index)
                Card(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("episodeDetail/${currentEpisode?.id}")
                        }
                ) {
                    Row {
                        Text(
                            text = currentEpisode?.episode.toString(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                        )
                        Text(
                            text = currentEpisode?.name.toString(),
                            fontSize = 20.sp,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }
        )
    }
}