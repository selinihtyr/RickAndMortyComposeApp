package com.selin.rickandmortycomposeapp.ui.theme.character

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.selin.rickandmortycomposeapp.data.retrofit.response.CharacterResponseList
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DetailScreen(
    navController: NavController,
    characterId: Int,
    viewModel: CharacterViewModel = hiltViewModel()
) {
    val character = remember { mutableStateOf<CharacterResponseList?>(null) }

    LaunchedEffect(characterId) {
        character.value = viewModel.getCharacterById(characterId)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Box(modifier = Modifier.fillMaxSize()) {
                CharacterImage(character = character.value)
                CharacterMainInfo(character = character.value)
            }
            CharacterInfoCard("Location", character.value?.location?.name ?: "")
            CharacterInfoCard("Origin", character.value?.origin?.name ?: "")
            EpisodeCard(character = character.value, navController = navController)
        }
    }
}

@Composable
fun CharacterImage(character: CharacterResponseList?) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        GlideImage(
            imageModel = character?.image ?: "",
            contentDescription = "Localized description",
        )
    }
}

@Composable
fun CharacterMainInfo(character: CharacterResponseList?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp, top = 330.dp)
            .background(Color.Transparent),
        elevation = CardDefaults.cardElevation(5.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(Color.White)
        ) {
            Text(
                text = character?.name ?: "",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Divider(
                color = Color.DarkGray,
                thickness = 1.dp,
                modifier = Modifier.padding(top = 8.dp, start = 24.dp, end = 24.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                CharacterAttribute(character?.species ?: "")
                CharacterAttribute(character?.status ?: "")
                CharacterAttribute(character?.gender ?: "")
            }
        }
    }
}

@Composable
fun CharacterAttribute(text: String) {
    Column {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun CharacterInfoCard(title: String, content: String) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp, top = 4.dp)
            .background(Color.Transparent),
        elevation = CardDefaults.cardElevation(5.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Unspecified,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = content,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
fun EpisodeCard(character: CharacterResponseList?, navController: NavController) {
    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp, top = 4.dp)
            .clickable {
                val episodeIds = character?.episode?.map {
                    it
                        .substringAfterLast("/")
                        .toInt()
                } ?: emptyList()
                navController.navigate("episodesFromCharacter/${episodeIds.joinToString(",")}")
            }
    ) {
        Text(
            text = "Episodes",
            modifier = Modifier
                .padding(16.dp)
                .background(Color.White)
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
    }
}