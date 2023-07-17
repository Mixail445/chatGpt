package com.example.chatgpt



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatgpt.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
         var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: ChatGPtViewModel = ViewModelProvider(this).get(ChatGPtViewModel::class.java)


        val llm = LinearLayoutManager(this)
        binding.rcView.layoutManager = llm
        viewModel.messageList.observe(this){Message->
            val adapter = messageAdapter(Message)
            binding.rcView.adapter = adapter
        }

        binding.imageButton.setOnClickListener {
            val question = binding.editTextTextPersonName.text.toString()
            viewModel.addtoChat(question,Message.SENT_BY_ME)
            binding.editTextTextPersonName.setText("")
            viewModel.callApi(question)
        }


  }

 }
