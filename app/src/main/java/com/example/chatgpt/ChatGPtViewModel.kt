package com.example.chatgpt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOError
import java.net.SocketTimeoutException


class ChatGPtViewModel:ViewModel (){
    private val _messageList = MutableLiveData<MutableList<Message>>()
    val messageList : LiveData<MutableList<Message>> get() = _messageList
    init {
        _messageList.value = mutableListOf()
    }

    fun addtoChat(message: String, sentBy: String){
                 val currentList = _messageList.value?: mutableListOf()
                 currentList.add(Message(message,sentBy))
                 _messageList.postValue(currentList)
        }

    private suspend fun handleapiresponse(response: Response<CompletionResponse>) {
withContext(Dispatchers.Main){
    if (response.isSuccessful){
        response.body()?.let{completionResponse ->
            val result = completionResponse.choices.firstOrNull()?.text
            if(result!=null){
                addresponse(result.trim())
                }else{
                addresponse("no choices faund")

            }}
        }else{addresponse("failed to get response" )}
    }

    }
    fun addresponse(response:String){
        _messageList.value?.removeAt(_messageList.value?.size?.minus(1)?:0)
        addtoChat(response,Message.SENT_BY_BOT)
    }

    fun callApi(question:String){
        addtoChat("Typing",Message.SENT_BY_BOT)
        val completionRequest = CompletionRequest(
            model = "text-davinci-003",
            prompt = question,
            max_tokens = 4000
        )
        viewModelScope.launch {
            try {
                val response = apiClient.apiServis.getCompletions(completionRequest)
                handleapiresponse(response)
            }catch (e: SocketTimeoutException){
                    addresponse("timeout: $e")
            }

        }
    }
}