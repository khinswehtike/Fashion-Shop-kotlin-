package com.example.newsapikotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapikotlin.api.ArticleApi
import com.example.newsapikotlin.model.ArticalResult
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ArticleViewModel :ViewModel(){
    var results:MutableLiveData<ArticalResult> = MutableLiveData()
    var articalLoadError: MutableLiveData<Boolean> = MutableLiveData()
    var loading:MutableLiveData<Boolean> =MutableLiveData()

    //getData
     fun getResults(): LiveData<ArticalResult> =results
    fun getErrors(): LiveData<Boolean> = articalLoadError
    fun getLoading(): LiveData<Boolean> =loading
    private val articleApi:ArticleApi= ArticleApi()

    //dataSet
    fun loadResults(){
        loading.value= true
        val call=articleApi.getResult()
        call.enqueue(object : retrofit2.Callback<ArticalResult>{

            override fun onFailure(call: Call<ArticalResult>, t: Throwable) {
                articalLoadError.value= true
                loading.value=false

            }

            override fun onResponse(call: Call<ArticalResult>, response: Response<ArticalResult>) {
                response?.isSuccessful.let {
                    loading.value= false
                    val resultList = ArticalResult(response?.body()?.articles ?: emptyList())
                    results.value=resultList
                    //to assign data in life data is .value
                }
            }

        })
    }

}