package com.example.biblioraapp
// Using Konfetti Library https://github.com/DanielMartinus/Konfetti
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.compose.OnParticleSystemUpdateListener
import nl.dionsegijn.konfetti.core.PartySystem
import nl.dionsegijn.konfetti.core.models.Shape


import com.example.biblioraapp.ui.theme.BiblioraAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BiblioraAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()
                    .background(Color.White)) { innerPadding ->
                   Main()
                }
            }
        }
    }
}

@Composable
fun Main(viewModel: ViewModelBook = viewModel()) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "bookList") {
        composable("bookList") {
            BookListScreen(navController = navController)
        }
        composable("bookDetails/{bookId}") { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId")?.toInt() ?: 0
            BookDetailsScreen(bookId = bookId, navController = navController)
        }
    }
/*
    val booksList by viewModel.books.collectAsState()

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(booksList) { book ->
            BookCard(book)
        }
    }*/
}

@Composable
fun BookDetailsScreen(bookId: Int, viewModel: ViewModelBook = viewModel(), navController: NavController) {
    val book = viewModel.books.collectAsState().value.find { it.id == bookId }

    if (book == null) {
        Text(text = "Book not found")
    } else {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 25.dp)
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = { navController.navigate("bookList") }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Bak",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }


            }

            Column(modifier = Modifier.padding(25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center)
                 {


                Image(
                    painter = painterResource(book.imageResourceId),
                    contentDescription = book.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
                        .height(500.dp)
                        .width(5.dp)
                )
                Text(text = book.title, style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(0.dp))
                Text(text = "${book.year}", style = MaterialTheme.typography.titleLarge)
                Text(text = book.description, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding( 15.dp))

            KonfettiUI()

        }}
    }
}

@Composable
fun BookListScreen(navController: NavController, viewModel: ViewModelBook = viewModel()) {
    val booksList by viewModel.books.collectAsState()
    Column( horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Your Books", style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(top=20.dp))
        LazyColumn(modifier = Modifier.padding(16.dp)) {

            items(booksList) { book ->
                BookCard(book = book, navController = navController)
            }
        }
    }
}

@Composable
fun BookCard(book: Book, navController: NavController) {

    var isClicked by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isClicked) 1.2f else 1f,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
    )

    val alpha by animateFloatAsState(
        targetValue = if (isClicked) 1f else 0.8f,
        animationSpec = tween(durationMillis = 300)
    )

    Card(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth()
            .clickable(
                onClick = { isClicked = !isClicked
                    navController.navigate("bookDetails/${book.id}")
                          },

            ),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {

        Column(modifier = Modifier.padding(0.dp)) {
            Box(
                modifier = Modifier
                    .padding(0.dp)
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale
                    )
            ) {
            Image(
                painter = painterResource(book.imageResourceId),
                contentDescription = "Dune",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .width(10.dp)
            )}


        }

    }
}

@Composable
fun KonfettiUI(viewModel: KonfettiViewModel = KonfettiViewModel()) {
    var isButtonEnabled by rememberSaveable { mutableStateOf(true) }
    val state: KonfettiViewModel.State by viewModel.state.observeAsState(
        KonfettiViewModel.State.Idle,
    )

    when (val newState = state) {
        KonfettiViewModel.State.Idle -> {
            Button(
                onClick = {
                    viewModel.rain()
                    isButtonEnabled = false
                },
                enabled = isButtonEnabled,
                colors = ButtonDefaults.buttonColors(containerColor = if (isButtonEnabled) Color.hsl(270f, .65f, .70f) else Color.Gray)

            ) {
                if(isButtonEnabled){
                    Text(
                        text = "I've Finished this book",
                        modifier = Modifier.padding(10.dp),
                        fontSize = 20.sp
                    )
                }else{
                Text(
                    text = "You've Finished this Book",
                    modifier = Modifier.padding(10.dp),
                    fontSize = 20.sp
                )
            }}
        }
        is KonfettiViewModel.State.Started -> {
            KonfettiView(
                modifier = Modifier.fillMaxSize(),
                parties = newState.party,
                updateListener = object : OnParticleSystemUpdateListener {
                    override fun onParticleSystemEnded(
                        system: PartySystem,
                        activeSystems: Int
                    ) {
                        if (activeSystems == 0) {
                            viewModel.ended()

                        }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BiblioraAppTheme {

    }
}