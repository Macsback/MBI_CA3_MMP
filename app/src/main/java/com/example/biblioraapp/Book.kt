package com.example.biblioraapp

import androidx.annotation.DrawableRes


data class Book(val id: Int,
                val title: String,
                val year: Int,
                val description: String,
                 val imageResourceId: String)