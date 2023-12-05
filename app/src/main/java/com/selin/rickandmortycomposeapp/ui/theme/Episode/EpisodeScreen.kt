package com.selin.rickandmortycomposeapp.ui.theme.Episode

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.selin.rickandmortycomposeapp.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EpisodesScreen(viewModel: EpisodeViewModel = hiltViewModel()) {
    viewModel.loadEpisodes()
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
                    Text(text = "Episodes", fontSize = 32.sp, fontWeight = FontWeight.Bold)
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
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {

                            }) {
                            Text(
                                text = list.value!![it].episode,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                )
            }
        }
    )
}