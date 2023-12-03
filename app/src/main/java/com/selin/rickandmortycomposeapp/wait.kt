package com.selin.rickandmortycomposeapp
/*
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.selin.rickandmortycomposeapp.viewmodel.RickAndMortyHomePageViewModel

@Composable
fun RickAndMortyHomePage(/*characterList: List<Character>*/) {
    val viewModel: RickAndMortyHomePageViewModel = viewModel()
    LaunchedEffect(key1 = true) {
        viewModel.loadCharacters()
    }
    viewModel.characterList.observeAsState().value?.let { characters ->
        Log.e("RickAndMortyList", "RickAndMortyList: ${characters.get(2).name}")
    }

}*/

/*
* @Composable
fun CharacterScreen(character: Character) {
    val characterImage = remember { mutableStateOf("") }
    val characterName = remember { mutableStateOf("") }

    val viewModel: RickAndMortyHomePageViewModel = viewModel()
    val characterList = viewModel.characterList.observeAsState(listOf())

    val localFocusManager = LocalFocusManager.current

    LaunchedEffect(key1 = true){
        viewModel.loadCharacters()
        characterName.value = incomingCharacter.name
        characterImage.value = incomingCharacter.image
    }

    LazyColumn{
        items(
            count = characterList.value!!.count(),
            itemContent = {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { /*karakter detay sayfasına git*/ }
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = characterList.value!![it].name)
                        GlideImage(
                            imageModel = characterList.value!![it].image,
                            contentDescription = characterList.value!![it].name,
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }
            }
        )
        localFocusManager.clearFocus()
    }
}*/