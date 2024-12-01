package com.example.biblioraapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun Main() {
    val booksList = listOf(
        Book("Dune", 1968,  "One of the pivotal works of literary science fiction, Frank Herbert’s sand-blasted epic details the internecine battles for control of the desert planet Arrakis and its precious resource, \\'spice\\'."),
        Book("Babel", 2022,  "One of the leading lights in the visceral grimdark fantasy genre, Kuang\\'s stunningly wrought novel is a compulsive dystopia about the power of language and the evils of authoritarianism, represented by the looming titular tower of translation."),
        Book("Percy Jackson", 2005,  "The first adventure for Percy Jackson sets the reluctant hero a ten-day deadline to find the true snatcher of Zeus\\' legendary lightning bolt in Riordan\\'s page-turning, myth-packed tale."),
    )

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(booksList) { book ->
            BookCard(book)
        }
    }
}

@Composable
fun BookCard(book: Book) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
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