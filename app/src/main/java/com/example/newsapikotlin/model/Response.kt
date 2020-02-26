package com.example.newsapikotlin.model



data class ArticalResult(val articles:List<Article>)
data class Article(
    val author:String,
    val title:String,
    val descriptor:String,
    val url:String,
    val urlToimage:String,
    val publishedAt:String,
    val content:String)

data class Source(val id:Any,val name:String)