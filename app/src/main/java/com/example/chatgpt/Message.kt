package com.example.chatgpt

 data class Message(

     var message: String,
     var sentBy: String)

 {companion object{

     const val SENT_BY_ME: String = "me"
     const val SENT_BY_BOT: String = "bot"
     }}





