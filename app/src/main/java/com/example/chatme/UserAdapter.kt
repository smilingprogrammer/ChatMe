package com.example.chatme

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatme.databinding.UserLayoutBinding
import com.google.firebase.auth.FirebaseAuth

class UserAdapter(val context: Context, private val userList: ArrayList<User>):
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

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ChatActivity::class.java)

            intent.putExtra("name", userList[position].name)
            intent.putExtra("uid", userList[position].uid)

            context.startActivity(intent)
        }
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