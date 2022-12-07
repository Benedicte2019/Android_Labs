package com.example.lab4.model

import androidx.lifecycle.ViewModel

class BlogModel: ViewModel() {
    var articles = mutableListOf<Article>()

    fun add(newFavArticle: Article){
        articles.add(newFavArticle)
    }
}