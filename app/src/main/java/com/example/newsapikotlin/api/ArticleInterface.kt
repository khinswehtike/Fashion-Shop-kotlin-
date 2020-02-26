package com.example.newsapikotlin.api



import com.example.newsapikotlin.model.ArticalResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ArticleInterface{
    @Headers("X-Api-Key:fe364fd4923f47c1b112a77b224c885d")
    @GET("v2/top-headlines?country=us")
    fun getArticles(): Call<ArticalResult>
}