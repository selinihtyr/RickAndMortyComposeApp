package com.selin.rickandmortycomposeapp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.selin.rickandmortycomposeapp.entity.character.Character
import com.selin.rickandmortycomposeapp.viewmodel.RickAndMortyHomePageViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CharacterScreen(character: Character){
    val viewModel: RickAndMortyHomePageViewModel = viewModel()
    val characterList = viewModel.characterList.observeAsState(listOf())

    LaunchedEffect(key1 = true) {
        viewModel.loadCharacters()
    }

    LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier
                        .size(200.dp)
                        .clickable {
                            //karakter detay sayfasına git
                        }
                ) {
                    Text(text = character.name)
                    GlideImage(
                        imageModel = character.image,
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}