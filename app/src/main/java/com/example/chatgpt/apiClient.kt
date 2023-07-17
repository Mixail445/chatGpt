package com.example.chatgpt

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object apiClient
{
    private const val BASE_URL = "https://api.openai.com/"
     val okkottp =   OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(okkottp).addConverterFactory(   GsonConverterFactory.create()).build()
    val apiServis :dataOpen = retrofit.create(dataOpen::class.java)
}