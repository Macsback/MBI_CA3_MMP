package com.example.biblioraapp


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelBook : ViewModel() {


    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books


  /*  init {
        fetchBooks("kotlin")
    }

    private fun fetchBooks(query: String) {
        // Launch coroutine to fetch books
        viewModelScope.launch {
            try {

                val response: BookResponse = RetrofitInstance.api.searchBooks(query)
                _books.value = response.docs
            } catch (e: Exception) {
                Log.e("BookViewModel", "Error fetching books", e)
            }
        }
    }*/
    init {
        _books.value = listOf(
            Book(1,"Dune", 1968,  "One of the pivotal works of literary science fiction, Frank Herbert’s sand-blasted epic details the internecine battles for control of the desert planet Arrakis and its precious resource, \'spice\'.", R.drawable.dune),
            Book(2,"Babel", 2022,  "One of the leading lights in the visceral grimdark fantasy genre, Kuang\'s stunningly wrought novel is a compulsive dystopia about the power of language and the evils of authoritarianism, represented by the looming titular tower of translation.",  R.drawable.babel),
            Book(3,"Percy Jackson", 2005,  "The first adventure for Percy Jackson sets the reluctant hero a ten-day deadline to find the true snatcher of Zeus\' legendary lightning bolt in Riordan\'s page-turning, myth-packed tale.",  R.drawable.pj),
            Book(4, "Lord of the Rings", 1988, "The first part of Tolkien's genre-defining masterpiece introduces arguably the most famous quest in fiction, as Frodo and his comrades set off to destroy the One Ring.",  R.drawable.lotr),
            Book(5,"A Scatter of Light", 2023, "Scatter of Light is a companion novel to the National Book Awards winner and New York Times bestseller Last Night at the Telegraph Club, and is about how the threads of family, inspiration, art, and identity are woven across generations.",  R.drawable.scatteroflight),
            Book(6,"Mistborn #1", 2006, "Kickstarting the Mistborn series in stunning style, Sanderson's wildly entertaining fantasy heist sees a motley assortment of criminals attempt to overthrow an all-powerful tyrant.",  R.drawable.mistborn),
            Book(7,"The Witcher", 1986, "Introducing Geralt the Witcher - revered and hated - who holds the line against the monsters plaguing humanity in the bestselling series that inspired the Witcher video games and a major Netflix show.",  R.drawable.thewitcher),
            Book(8,"The Blighted Stars", 2023, "When a spy and her mortal enemy crash-land on a dying planet, she must figure out how to survive long enough to uncover the deadly, galaxy-spanning conspiracy that landed them there. The Blighted Stars is the first book in an epic new space-opera trilogy from the author of the Philip K. Dick-nominated Velocity Weapon.",  R.drawable.blightedstars),
            Book(9,"Intermezzo", 2024, "Two brothers navigate the turmoil and joy of love after the passing of their father in this breathtaking novel from the hugely acclaimed author of Normal People and Conversations With Friends.",  R.drawable.inter),
            Book(10,"Starter Villain", 2023, "A hiss-terical story about the (un)luckiest nepo-baby of all time, Starter Villain follows an everyman who inherits his uncle's supervillain empire.",  R.drawable.villain),

            )
    }
}