package com.example.chatme

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatme.databinding.UserLayoutBinding

class UserAdapter(val userList: ArrayList<User>):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

        inner class UserViewHolder(private val binding: UserLayoutBinding):
            RecyclerView.ViewHolder(binding.root){
            fun bind(user: User){
                binding.txtName.text = user.name
            }
        }

    override fun getItemCount(): Int {
        return userList.size
    }
}