package com.selin.rickandmortycomposeapp.ui.theme.episode

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.selin.rickandmortycomposeapp.R
import com.selin.rickandmortycomposeapp.data.retrofit.response.EpisodeResponseList
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EpisodeDetailScreen(
    navController: NavController,
    episodeId: Int,
    viewModel: EpisodeViewModel = hiltViewModel()
) {
    val episode = remember { mutableStateOf<EpisodeResponseList?>(null) }

    LaunchedEffect(episodeId) {
        episode.value = viewModel.getEpisodeById(episodeId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("home")
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Localized description"
                        )
                    }
                }, title = {
                    Text(text = "Episode", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                }
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 56.dp)
            ) {
                items(
                    count = 1,
                    itemContent = {
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = episode.value?.name ?: "",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = episode.value?.episode ?: "")
                                Column(
                                    verticalArrangement = Arrangement.SpaceAround,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(text = "Air Date", fontSize = 14.sp)
                                    Text(
                                        text = episode.value?.airDate ?: "",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                        Card {
                            Column {
                                Text(text = "Characters", fontSize = 14.sp)
                                LazyVerticalStaggeredGrid(
                                    columns = StaggeredGridCells.Fixed(3),
                                    modifier = Modifier.fillMaxSize(),
                                    contentPadding = PaddingValues(2.dp)
                                ) {
                                    items(
                                        count = 10,
                                        itemContent = {
                                            Card(
                                                modifier = Modifier
                                                    .padding(4.dp)
                                                    .fillMaxWidth()
                                            ) {
                                                Box {
                                                    Text(
                                                        text = "character name",
                                                        fontSize = 14.sp,
                                                        modifier = Modifier.padding(8.dp)
                                                    )
                                                    GlideImage(
                                                        imageModel = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                                                    )
                                                }
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                )
            }
        })
}