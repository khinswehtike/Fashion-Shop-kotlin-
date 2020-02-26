package com.example.newsapikotlin

import java.text.SimpleDateFormat
import java.util.*


fun tosimpleString(publishedAt:String):String{
    val inputFormatter= SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",
        Locale.ENGLISH)
    val outputFormatter= SimpleDateFormat("EEE,d MMM yyyy HH:mm",Locale.getDefault())
    val date=inputFormatter.parse(publishedAt)
    return outputFormatter.format(date)

}
