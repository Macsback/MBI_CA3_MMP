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
            Book(1,"Dune", 1968,  "One of the pivotal works of literary science fiction, Frank Herbert’s sand-blasted epic details the internecine battles for control of the desert planet Arrakis and its precious resource, \'spice\'.", "https://cdn11.bigcommerce.com/s-aweq463/images/stencil/1280x1280/products/38807/102648/9781473233959__16861.1636040771.jpg?c=2"),
            Book(2,"Babel", 2022,  "One of the leading lights in the visceral grimdark fantasy genre, Kuang\'s stunningly wrought novel is a compulsive dystopia about the power of language and the evils of authoritarianism, represented by the looming titular tower of translation.", "https://images.squarespace-cdn.com/content/v1/57d5e7ffbebafba39bec0650/1661406578580-D7RAUDTLJCEI22QQS8QU/babel.jpg"),
            Book(3,"Percy Jackson", 2005,  "The first adventure for Percy Jackson sets the reluctant hero a ten-day deadline to find the true snatcher of Zeus\' legendary lightning bolt in Riordan\'s page-turning, myth-packed tale.",  "https://m.media-amazon.com/images/I/91qH6txtOaL._AC_UF1000,1000_QL80_.jpg"),
            Book(4, "Lord of the Rings", 1988, "The first part of Tolkien's genre-defining masterpiece introduces arguably the most famous quest in fiction, as Frodo and his comrades set off to destroy the One Ring.", "https://m.media-amazon.com/images/I/81nV6x2ey4L._AC_UF894,1000_QL80_.jpg"),
            Book(5,"A Scatter of Light", 2023, "Scatter of Light is a companion novel to the National Book Awards winner and New York Times bestseller Last Night at the Telegraph Club, and is about how the threads of family, inspiration, art, and identity are woven across generations.",  "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1649322468l/60093178.jpg"),
            Book(6,"Mistborn #1", 2006, "Kickstarting the Mistborn series in stunning style, Sanderson's wildly entertaining fantasy heist sees a motley assortment of criminals attempt to overthrow an all-powerful tyrant.",  "https://www.gollancz.co.uk/wp-content/uploads/2018/07/hbg-title-9780575089914-972.jpg"),
            Book(7,"The Witcher", 1986, "Introducing Geralt the Witcher - revered and hated - who holds the line against the monsters plaguing humanity in the bestselling series that inspired the Witcher video games and a major Netflix show.",  "https://preview.redd.it/new-hardcover-collection-from-orbit-books-v0-gey3xzd92oj91.jpg?width=640&crop=smart&auto=webp&s=949d23f14f722c63d35048e327029f3a647c3673"),
            Book(8,"The Blighted Stars", 2023, "When a spy and her mortal enemy crash-land on a dying planet, she must figure out how to survive long enough to uncover the deadly, galaxy-spanning conspiracy that landed them there. The Blighted Stars is the first book in an epic new space-opera trilogy from the author of the Philip K. Dick-nominated Velocity Weapon.",  "https://www.hachettebookgroup.com/wp-content/uploads/2023/10/9780316290791.jpg"),
            Book(9,"Intermezzo", 2024, "Two brothers navigate the turmoil and joy of love after the passing of their father in this breathtaking novel from the hugely acclaimed author of Normal People and Conversations With Friends.",  "https://www.bookstation.ie/wp-content/uploads/2024/02/9780571365470.jpg"),
            Book(10,"Starter Villain", 2023, "A hiss-terical story about the (un)luckiest nepo-baby of all time, Starter Villain follows an everyman who inherits his uncle's supervillain empire.",  "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1683564155l/61885029.jpg"),

            )
    }
}