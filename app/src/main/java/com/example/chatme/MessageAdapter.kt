package com.example.chatme

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatme.databinding.ReceiveBinding
import com.example.chatme.databinding.SentBinding
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val messageList: ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_SENT = 1
    val ITEM_RECEIVE = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1){
            //inflate receive
            return ReceiveViewHolder(
                ReceiveBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        } else {
            //inflate sent
            return SentViewHolder(
                SentBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
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

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]

        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            return ITEM_SENT
        } else {
            return ITEM_RECEIVE
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }
}