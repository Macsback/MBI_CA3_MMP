package com.example.biblioraapp

data class BookResponse(
    val numFound: Int, // Total number of books found
    val docs: List<Book> // List of books
)