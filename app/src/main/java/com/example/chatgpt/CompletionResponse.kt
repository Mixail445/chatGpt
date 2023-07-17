package com.example.chatgpt

data class CompletionResponse(
    val choices: List<Choice>,
    val created: Int,
    val id: String,
    val model: String,
    val obj: String,
    val usage: Usage
)
data class Choice(
    val finish_reason: String,
    val index: Int,
    val logprobs: Any,
    val text: String
)
data class Usage(
    val completion_tokens: Int,
    val prompt_tokens: Int,
    val total_tokens: Int
)
