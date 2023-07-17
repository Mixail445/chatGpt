package com.example.chatgpt

import com.example.chatgpt.apiopen.MY_API
import retrofit2.Response


import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface dataOpen {
    @Headers("Authorization: Bearer $MY_API")
    @POST("v1/completions")
    suspend fun getCompletions(@Body CompletionResponse:CompletionRequest): Response<CompletionResponse>
}