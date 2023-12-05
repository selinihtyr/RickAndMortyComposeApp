package com.selin.rickandmortycomposeapp.ui.theme.Character

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.selin.rickandmortycomposeapp.R
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharacterScreen(viewModel: CharacterViewModel = hiltViewModel()) {
    viewModel.loadCharacters()
    val list = viewModel.list.observeAsState(listOf())

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Localized description"
                        )
                    }
                }, title = {
                    Text(text = "Characters", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                })
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 56.dp)
            ) {
                items(
                    count = list.value!!.count(),
                    itemContent = {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {

                                }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),

                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                GlideImage(
                                    imageModel = list.value!![it].image,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(CircleShape),
                                    contentDescription = "Character Image",

                                    )
                                Text(
                                    text = list.value!![it].name,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(start = 50.dp)
                                )
                            }
                        }
                    }
                )
            }
        }
    )
}