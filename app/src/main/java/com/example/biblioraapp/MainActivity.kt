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
        Book("Dune", 1968,  "One of the pivotal works of literary science fiction, Frank Herbert’s sand-blasted epic details the internecine battles for control of the desert planet Arrakis and its precious resource, \'spice\'."),
        Book("Babel", 2022,  "One of the leading lights in the visceral grimdark fantasy genre, Kuang\'s stunningly wrought novel is a compulsive dystopia about the power of language and the evils of authoritarianism, represented by the looming titular tower of translation."),
        Book("Percy Jackson", 2005,  "The first adventure for Percy Jackson sets the reluctant hero a ten-day deadline to find the true snatcher of Zeus\' legendary lightning bolt in Riordan\'s page-turning, myth-packed tale."),
        Book("Lord of the Rings", 1988, "The first part of Tolkien's genre-defining masterpiece introduces arguably the most famous quest in fiction, as Frodo and his comrades set off to destroy the One Ring."),
        Book("A Scatter of Light", 2023, "Scatter of Light is a companion novel to the National Book Awards winner and New York Times bestseller Last Night at the Telegraph Club, and is about how the threads of family, inspiration, art, and identity are woven across generations."),
        Book("Mistborn #1", 2006, "Kickstarting the Mistborn series in stunning style, Sanderson's wildly entertaining fantasy heist sees a motley assortment of criminals attempt to overthrow an all-powerful tyrant."),
        Book("The Witcher", 1986, "Introducing Geralt the Witcher - revered and hated - who holds the line against the monsters plaguing humanity in the bestselling series that inspired the Witcher video games and a major Netflix show."),
        Book("The Blighted Stars", 2023, "When a spy and her mortal enemy crash-land on a dying planet, she must figure out how to survive long enough to uncover the deadly, galaxy-spanning conspiracy that landed them there. The Blighted Stars is the first book in an epic new space-opera trilogy from the author of the Philip K. Dick-nominated Velocity Weapon."),
        Book("Intermezzo", 2024, "Two brothers navigate the turmoil and joy of love after the passing of their father in this breathtaking novel from the hugely acclaimed author of Normal People and Conversations With Friends."),
        Book("Starter Villain", 2023, "A hiss-terical story about the (un)luckiest nepo-baby of all time, Starter Villain follows an everyman who inherits his uncle's supervillain empire."),

        )

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