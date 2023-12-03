package com.selin.rickandmortycomposeapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.ImagePainter
import com.google.gson.Gson
import com.selin.rickandmortycomposeapp.entity.character.Character
import com.selin.rickandmortycomposeapp.entity.character.HomePage
import com.selin.rickandmortycomposeapp.ui.theme.RickAndMortyComposeAppTheme
import com.selin.rickandmortycomposeapp.viewmodel.RickAndMortyHomePageViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.mainBackground)
                ) {
                    ScreenTransition()
                }
            }
        }
    }
}

@Composable
fun ScreenTransition() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable(
            "character/{character}",
            arguments = listOf(navArgument("character") { type = NavType.IntType })
        ) {
            val json = it.arguments?.getString("character")
            val character = Gson().fromJson(json, Character::class.java)
            CharacterScreen(character)
        }
        composable("episode") {
            // EpisodeScreen()
        }
        composable("location") {
            // LocationScreen()
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    val cartList = listOf(
        HomePage(1, "Characters", R.drawable.character),
        HomePage(2, "Episodes", R.drawable.episodes),
        HomePage(3, "Locations", R.drawable.location)
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            count = cartList.size,
            itemContent = {
                val character = cartList[it]
                Card(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .clickable {

                    }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = character.name,
                            fontSize = 32.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 120.dp)
                        )
                        Image(
                            painter = painterResource(id = character.image),
                            contentDescription = null,
                            modifier = Modifier.height(120.dp)
                        )
                    }
                }
            }
        )
    }
}

