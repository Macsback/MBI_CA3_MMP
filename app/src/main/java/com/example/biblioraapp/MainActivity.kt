package com.example.biblioraapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.*


import com.example.biblioraapp.ui.theme.BiblioraAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BiblioraAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   Main()
                }
            }
        }
    }
}

@Composable
fun Main(viewModel: ViewModelBook = viewModel()) {



    val booksList by viewModel.books.collectAsState()

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(booksList) { book ->
            BookCard(book)
        }
    }
}

@Composable
fun BookCard(book: Book) {

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
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(
                onClick = { isClicked = !isClicked },

            ),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        colors =  CardDefaults.cardColors(Color.LightGray.copy(alpha = alpha)) // Change color to provide better feedback
    ) {

        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale
                    )
            ) {
            Image(
                painter = painterResource(R.drawable.dune),
                contentDescription = "Dune",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp))}
           Text(text = book.title)
            Text(text = "${book.year}")
            Text(text = book.description)
        }

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BiblioraAppTheme {

    }
}