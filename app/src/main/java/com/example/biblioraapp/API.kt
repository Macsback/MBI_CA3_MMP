package com.example.biblioraapp


import com.example.biblioraapp.BookResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val URL = "https://openlibrary.org/"

interface OpenLibraryApi {

    // Function to search for books by title
    @GET("search.json")
    suspend fun searchBooks(
        @Query("title") title: String,  // Query parameter for book title
        @Query("page") page: Int = 1    // Page number for pagination
    ): BookResponse
}

// Retrofit instance
object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())  // Use Gson converter to parse JSON
            .build()
    }

    val api: OpenLibraryApi by lazy {
        retrofit.create(OpenLibraryApi::class.java)
    }
}