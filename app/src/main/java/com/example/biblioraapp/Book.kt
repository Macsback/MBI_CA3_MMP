package com.example.biblioraapp


data class Book(
    val title: String, // Book title
    val author_name: List<String>?, // List of authors
    val first_publish_year: Int?, // First publication year
    val cover_i: Int?) {

}