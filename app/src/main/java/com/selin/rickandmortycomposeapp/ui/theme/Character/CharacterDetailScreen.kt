package com.selin.rickandmortycomposeapp.ui.theme.Character

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
fun DetailScreen(navController: NavController, characterId: Int, viewModel: CharacterViewModel = hiltViewModel()) {
    val character = remember { mutableStateOf<CharacterResponseList?>(null) }

    LaunchedEffect(characterId) {
        character.value = viewModel.getCharacterById(characterId)
        Log.d("DetailScreen", "DetailScreen: ${character.value}")
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        item {
            Box(modifier = Modifier.fillMaxSize()) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp)
                ) {
                    GlideImage(
                        imageModel = character.value?.image ?: "",
                        contentDescription = "Localized description",
                    )
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp, top = 330.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = character.value?.name ?: "",
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
                            Column {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "Person Icon",
                                    tint = Color.Black,
                                    modifier = Modifier.size(48.dp)
                                )
                                Text(
                                    text = character.value?.species ?: "",
                                )
                            }
                            Column {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Person Icon",
                                    tint = Color.Black,
                                    modifier = Modifier.size(48.dp)
                                )
                                Text(
                                    text = character.value?.status ?: "",
                                )
                            }
                            Column {
                                Icon(
                                    imageVector = Icons.Default.Face,
                                    contentDescription = "Person Icon",
                                    tint = Color.Black,
                                    modifier = Modifier.size(48.dp)
                                )
                                Text(
                                    text = character.value?.gender ?: "",
                                )
                            }
                        }
                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 24.dp, end = 24.dp, top = 4.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Location",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Unspecified
                    )
                    Text(
                        text = character.value?.location?.name ?: "",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 24.dp, end = 24.dp, top = 4.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Origin", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Text(
                        text = character.value?.origin?.name ?: "",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Card(modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp, top = 4.dp)
                .clickable {
                    Log.d("DetailScreen", "DetailScreen: ${character.value?.episode}")
                    val episodeIds = character.value?.episode?.map {
                        it.substringAfterLast("/").toInt()
                    } ?: emptyList()
                    navController.navigate("episodesFromDetailScreen/${episodeIds.joinToString(",")}")
                }) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Episodes", fontSize = 14.sp, fontWeight = FontWeight.Bold)

                }
            }
        }
    }
}


