package com.example.newsapikotlin.api

import com.example.newsapikotlin.model.ArticalResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArticleApi {
    private val articleInterface: ArticleInterface
    companion object{
        const val BASE_URL="https://newsapi.org/"
    }
    init {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        articleInterface=retrofit.create(ArticleInterface::class.java)
    }
    fun getResult():Call<ArticalResult>{
        return articleInterface.getArticles()
    }
}