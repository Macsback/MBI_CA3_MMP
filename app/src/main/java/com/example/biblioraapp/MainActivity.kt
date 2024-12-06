package com.example.biblioraapp
// Using Konfetti Library https://github.com/DanielMartinus/Konfetti

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.compose.OnParticleSystemUpdateListener
import nl.dionsegijn.konfetti.core.PartySystem
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
        BoxWithConstraints {
            if (maxWidth < 400.dp) {

                Column(
                    modifier = Modifier.padding(top = 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(5.dp, top = 20.dp)
                            .height(50.dp).background(Color.hsl(251f, .13f, .50f,.08f)),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,


                        ) {
                        IconButton(onClick = { navController.navigate("bookList") }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.primary,

                                )
                        }

                        Text(
                            text = "Your Books",
                            style = MaterialTheme.typography.headlineMedium,
                            fontFamily = FontFamily.Serif,
                            fontSize = 20.sp
                        )

                    }

                    Column(
                        modifier = Modifier.padding(10.dp, top = 0.dp)
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    )
                    {
                        Log.i("DetailsScreen", "Image Loading...");
                        Image(
                            painter = rememberAsyncImagePainter(book.imageResourceId),
                            contentDescription = book.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp, bottom = 0.dp, top=10.dp)
                                .height(400.dp)
                                .width(10.dp),


                            )
                        Text(
                            text = book.title,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(top = 10.dp),
                            fontFamily = FontFamily.Serif,
                            fontSize = 25.sp
                        )
                        Text(
                            text = "${book.year}",
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 18.sp
                        )
                        Text(

                            text = book.description,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(20.dp).fillMaxWidth(),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )

                        KonfettiUI()

                    }
                }
            } else {
            Column(
                modifier = Modifier.padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(10.dp, bottom = 0.dp).height(70.dp).background(Color.hsl(251f, .13f, .50f,.08f)),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,


                    ) {
                    IconButton(onClick = { navController.navigate("bookList") }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary,

                            )
                    }

                    Text(
                        text = "Your Books",
                        style = MaterialTheme.typography.headlineMedium,
                        fontFamily = FontFamily.Serif
                    )

                }

                Column(
                    modifier = Modifier.padding(20.dp, top = 0.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                )
                {
                    Log.i("DetailsScreen", "Image Loading...");
                    Image(
                        painter = rememberAsyncImagePainter(book.imageResourceId),
                        contentDescription = book.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 0.dp)
                            .height(550.dp)
                            .width(8.dp),


                        )
                    Text(
                        text = book.title,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 10.dp),
                        fontFamily = FontFamily.Serif,
                        fontSize = 36.sp
                    )
                    Text(
                        text = "${book.year}",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 26.sp
                    )
                    Text(

                        text = book.description,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(15.dp).fillMaxWidth(),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )

                    KonfettiUI()

                }
            }
        }
    }
}
        }



@Composable
fun BookListScreen(navController: NavController, viewModel: ViewModelBook = viewModel()) {
    val booksList by viewModel.books.collectAsState()
    BoxWithConstraints {
        if(maxWidth<400.dp) {
            Column(

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .background(Color.hsl(270f, .65f, .70f))
                        .height(70.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center

                ) {
                Text(
                    text = "Your Books",
                    fontFamily = FontFamily.Serif,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(top = 0.dp),
                    color = Color.White


                )}
                LazyColumn(modifier = Modifier.padding(10.dp)) {
                    Log.i("BookScreen", "Loading Items...");
                    items(booksList) { book ->
                        BookCard(book = book, navController = navController)
                    }
                }
            }
        }else{  Column(

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .background(Color.hsl(270f, .65f, .70f))
                    .height(70.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
            Text(
                text = "Your Books",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(top = 20.dp),
                color = Color.White,
                fontFamily = FontFamily.Serif
            )}
            LazyColumn(modifier = Modifier.padding(0.dp)) {
                Log.i("BookScreen", "Loading Items...");
                items(booksList) { book ->
                    BookCard(book = book, navController = navController)
                }
            }
        }}
    }
}

@Composable
fun BookCard(book: Book, navController: NavController) {

    var isClicked by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isClicked) 1.2f else 1f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )



    Card(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth()
            .clickable(
                onClick = { isClicked = !isClicked
                    navController.navigate("bookDetails/${book.id}")
                          },

            )
            .background(Color.White),

    ) {

        Column(modifier = Modifier.padding(5.dp)) {
            BoxWithConstraints (
                modifier = Modifier
                    .padding(0.dp)
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale
                    ).background(Color.White)
            ) {
                Log.i("BookScreen", "Image Loading..." )
                if(maxWidth < 400.dp) {

                    Image(
                        painter = rememberAsyncImagePainter(book.imageResourceId),
                        contentDescription = "Dune",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(450.dp)
                            .width(1.dp)
                            .background(Color.White)
                            .clip(RectangleShape),
                       //contentScale = ContentScale.Crop

                    )

                }  else{

                    Image(
                        painter = rememberAsyncImagePainter(book.imageResourceId),
                        contentDescription = "Dune",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(600.dp)
                            .width(10.dp)
                            .background(Color.White)
                            .clip(RectangleShape),
                        contentScale = ContentScale.Crop
                    )
                }

            }


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
                colors = ButtonDefaults.buttonColors(containerColor = if (isButtonEnabled) Color.hsl(270f, .65f, .70f) else Color.Gray),
                modifier = Modifier.padding(10.dp, bottom = 20.dp)

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