package com.example.lab4.model

data class Article(val id: String, val title: String, val description: String)

// sample data
val sampleArticles = listOf(
        Article("1", "Financial Crisis Amid COVID-19", "Among the people most affected by pandemic are children from poor families..."),
        Article("2", "Financial Crisis", "30% of infants in Brazil do not have access to baby formula..."),
        Article("3", "Monetary Inflation", "30% of infants in Brazil do not have access to baby formula...")
    )

