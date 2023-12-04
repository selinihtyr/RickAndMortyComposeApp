package com.selin.rickandmortycomposeapp.ui.theme.HomePage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.selin.rickandmortycomposeapp.R
import com.selin.rickandmortycomposeapp.data.model.Character
import com.selin.rickandmortycomposeapp.data.local.CharacterHomePage
import com.selin.rickandmortycomposeapp.ui.theme.CharacterScreen.CharacterScreen
import com.selin.rickandmortycomposeapp.ui.theme.RickAndMortyComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            arguments = listOf(navArgument("character") { type = NavType.StringType })
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
        CharacterHomePage(1, "Characters", R.drawable.character),
        CharacterHomePage(2, "Episodes", R.drawable.episodes),
        CharacterHomePage(3, "Locations", R.drawable.location)
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
                        handleItemClick(character, navController)
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

fun handleItemClick(character: CharacterHomePage, navController: NavController) {
    val characterJson = Gson().toJson(character)
    when (character.id) {
        1 -> {
            navController.navigate("character/$characterJson")
        }

        2 -> {
            navController.navigate("episode/$characterJson")
        }

        3 -> {
            navController.navigate("location/$characterJson")
        }
    }
}


