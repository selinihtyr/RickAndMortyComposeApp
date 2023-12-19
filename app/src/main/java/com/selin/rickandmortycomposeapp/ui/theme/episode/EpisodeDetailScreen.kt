package com.selin.rickandmortycomposeapp.ui.theme.episode

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.selin.rickandmortycomposeapp.R
import com.selin.rickandmortycomposeapp.data.retrofit.response.CharacterResponseList
import com.selin.rickandmortycomposeapp.data.retrofit.response.EpisodeResponseList
import com.selin.rickandmortycomposeapp.ui.theme.character.handleBackPressed
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EpisodeDetailScreen(
    episodeId: Int,
    navController: NavController,
    viewModel: EpisodeViewModel = hiltViewModel()
) {
    val episode = viewModel.episode.collectAsState().value

    LaunchedEffect(episodeId) {
        viewModel.getEpisodeById(episodeId)
    }

    Scaffold(
        topBar = { TopBar(navController) },
        content = {
            EpisodeContent(episode, navController)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                handleBackPressed(navController)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Localized description"
                )
            }
        }, title = {
            Text(text = "Episode", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorResource(id = R.color.mainBackground)
        )
    )
}

@Composable
fun EpisodeContent(episode: EpisodeResponseList?, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 56.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EpisodeNameCard(episode)
        AirDateCard(episode)
        CharactersCard(episode, navController)
    }
}

@Composable
fun EpisodeNameCard(episode: EpisodeResponseList?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white)
        )
    ) {
        Text(
            text = episode?.name ?: "",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun AirDateCard(episode: EpisodeResponseList?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white)
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "Air Date:",
                fontSize = 14.sp,
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = episode?.airDate ?: "",
                fontSize = 18.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 30.dp)
            )
        }
    }
}

@Composable
fun CharactersCard(
    episode: EpisodeResponseList?,
    navController: NavController,
    viewModel: EpisodeViewModel = hiltViewModel()
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white)
        )
    ) {
        Column {
            Text(
                text = "Characters",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold
            )
            LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                items(episode?.characters?.size ?: 0) { index ->
                    episode?.characters?.get(index)?.substringAfterLast("/")
                        ?.toIntOrNull()?.let { characterId ->
                            val character =
                                remember { mutableStateOf<CharacterResponseList?>(null) }
                            LaunchedEffect(characterId) {
                                character.value = viewModel.getCharacterById(characterId)
                            }
                            CharacterImage(character.value, navController)
                        }
                }
            }
        }
    }
}

@Composable
fun CharacterImage(character: CharacterResponseList?, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable {
                navController.navigate("characterDetailScreen/${character?.id}")
            },
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white)
        )
    ) {
        Box {
            GlideImage(
                imageModel = character?.image ?: "",
                contentDescription = "Localized description",
            )
            Text(text = character?.name ?: "", modifier = Modifier.align(Alignment.Center))
        }
    }
}