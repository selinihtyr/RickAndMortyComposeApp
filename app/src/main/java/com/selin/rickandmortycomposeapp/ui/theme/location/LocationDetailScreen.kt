package com.selin.rickandmortycomposeapp.ui.theme.location

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.selin.rickandmortycomposeapp.R
import com.selin.rickandmortycomposeapp.data.retrofit.response.CharacterResponseList
import com.skydoves.landscapist.glide.GlideImage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDetailScreen(
    viewModel: LocationViewModel = hiltViewModel(),
    locationId: Int
) {
    val location = viewModel.location.collectAsState().value
    val characterCount = location?.residents?.size ?: 0

    LaunchedEffect(location) {
        viewModel.getLocationById(locationId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        //önceki sayfaya dön
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = ""
                        )
                    }
                }, title = {
                    Text(text = "Location", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                })
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 56.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = location?.name ?: "",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .padding(10.dp)
                            .height(IntrinsicSize.Min)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = location?.type ?: "",
                            fontSize = 18.sp,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(end = 30.dp)
                        )
                        Divider(
                            color = Color.Gray,
                            thickness = 1.dp,
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(1.dp)
                        )
                        Text(
                            text = location?.dimension ?: "",
                            fontSize = 18.sp,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 30.dp)
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column {
                        Text(
                            text = "Characters",
                            fontSize = 24.sp,
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.CenterHorizontally),
                            fontWeight = FontWeight.Bold
                        )
                        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                            items(characterCount) { index ->
                                location?.residents?.get(index)
                                    ?.substringAfterLast("/")?.toIntOrNull()?.let { characterId ->
                                        LocationBox(
                                            characterId = characterId,
                                            viewModel = viewModel
                                        )
                                    }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun LocationBox(characterId: Int, viewModel: LocationViewModel) {
    val character = remember { mutableStateOf<CharacterResponseList?>(null) }

    LaunchedEffect(characterId) {
        character.value = viewModel.getCharacterById(characterId)
    }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(100.dp)
            .clickable {
                //navigate to character detail
            }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            GlideImage(
                imageModel = character.value?.image ?: "",
                contentDescription = "Localized description",
            )
            Text(
                text = character.value?.name ?: "",
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Center),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}