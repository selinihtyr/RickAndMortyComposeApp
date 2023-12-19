package com.selin.rickandmortycomposeapp.ui.theme.character

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.selin.rickandmortycomposeapp.R
import com.selin.rickandmortycomposeapp.data.retrofit.response.CharacterResponseList
import com.selin.rickandmortycomposeapp.ui.theme.ShimmerEffect
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharacterScreen(navController: NavController, viewModel: CharacterViewModel = hiltViewModel()) {
    val lazyPagingItems = viewModel.paging.collectAsLazyPagingItems()

    LaunchedEffect(key1 = true) {
        viewModel.loadCharacters()
    }

    Scaffold(
        topBar = {
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
                },
                title = {
                    Text(text = "Characters", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorResource(id = R.color.mainBackground)
                )
            )
        },
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 56.dp)
                        .background(color = colorResource(id = R.color.mainBackground))
                ) {
                    items(
                        count = lazyPagingItems.itemCount,
                        itemContent = {
                            lazyPagingItems[it]?.let { character ->
                                CharacterItem(navController, character)
                            }
                        }
                    )
                }
            }
            if (lazyPagingItems.loadState.refresh is LoadState.Loading) {
                ShimmerEffectUi()
            }
        }
    )
}
var lastBackPressedTime: Long = 0
const val BACK_PRESS_INTERVAL = 2000L // 2 saniye

fun handleBackPressed(navController: NavController) {
    val currentTime = System.currentTimeMillis()

    if (currentTime - lastBackPressedTime > BACK_PRESS_INTERVAL) {
        navController.popBackStack()
    } else {
        navController.popBackStack("home", false)
    }
    lastBackPressedTime = currentTime
}

@Composable
fun CharacterItem(navController: NavController, character: CharacterResponseList) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                navController.navigate("characterDetailScreen/${character.id}")
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            GlideImage(
                imageModel = character.image,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentDescription = "Character Image",
            )
            Text(
                text = character.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 50.dp)
            )
        }
    }
}

@Composable
fun ShimmerEffectUi() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 56.dp)
    ) {
        items(
            count = 10,
            itemContent = {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(brush = ShimmerEffect())
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .background(brush = ShimmerEffect())
                    ) {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                                .background(brush = ShimmerEffect())
                        )
                    }
                }
            }
        )
    }
}