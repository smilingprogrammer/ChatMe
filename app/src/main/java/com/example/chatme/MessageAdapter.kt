package com.example.chatme

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatme.databinding.ReceiveBinding
import com.example.chatme.databinding.SentBinding

class MessageAdapter(val messageList: ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }


        inner class SentViewHolder(private val binding: SentBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(message: Message){
                    binding.txtSentMessage.text = message.message
                }
        }

        inner class ReceiveViewHolder(private val binding: ReceiveBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind1(message: Message){
                    binding.txtReceiveMessage.text = message.message
                }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.javaClass == SentViewHolder::class.java){
            val viewHolder =  holder as SentViewHolder
            holder.bind(messageList[position])
        } else {
            val viewHolder = holder as ReceiveViewHolder
            holder.bind1(messageList[position])
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}