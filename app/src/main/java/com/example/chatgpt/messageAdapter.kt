package com.example.chatgpt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.chatgpt.Message.Companion.SENT_BY_BOT
import com.example.chatgpt.Message.Companion.SENT_BY_ME
import com.example.chatgpt.databinding.ChatItemBinding




class messageAdapter( private val messageList: List<Message> ):RecyclerView.Adapter<messageAdapter.Holder>() {

    companion object {
        const val SENT_BY_ME = 0
        const val SENT_BY_BOT = 1
    }
    class Holder(view: View):RecyclerView.ViewHolder(view) {
val binding = ChatItemBinding.bind(view)
        fun bind(message: Message) {
            when (message.sentBy) {
                Message.SENT_BY_ME -> {
                    binding.rightChatTextView?.text = message.message

                }
                else -> {
                    binding.leftChatTextView?.text = message.message

                }
            }}
    }
    override fun getItemViewType(position: Int): Int {
        val message = messageList[position]
        return when (message.sentBy) {
            Message.SENT_BY_ME -> SENT_BY_ME
            else -> SENT_BY_BOT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val message = messageList[position]
        holder.bind(message)
}}