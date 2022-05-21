package com.example.chatme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatme.databinding.ActivityChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ChatActivity : AppCompatActivity() {

    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var sendButton: ImageView
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: ArrayList<Message>
    private lateinit var mDbRef: DatabaseReference

    var senderRoom: String? = null
    var receiverRoom: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mDbRef = FirebaseDatabase.getInstance().getReference()

        val name = intent.getStringExtra("name")
        val receiverUid = intent.getStringExtra("uid")

        val senderUid = FirebaseAuth.getInstance().currentUser?.uid

        senderRoom = receiverUid + senderUid
        receiverRoom = senderUid + receiverUid

        supportActionBar?.title = name

        chatRecyclerView = binding.chatRecyclerView
        messageBox = binding.messageBox
        sendButton = binding.sendButton
        messageList = ArrayList()
        messageAdapter = MessageAdapter(messageList)

        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = messageAdapter

        //logic for adding data to recyclerView
        mDbRef.child("chats").child(senderRoom!!).child("messages")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    messageList.clear()

                    for (postSnapshot in snapshot.children){
                        val message = postSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)
                    }

                    messageAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

        //getting message to database
        sendButton.setOnClickListener {

            val message = messageBox.text.toString()
            val messageObject = Message(message, senderUid)

            mDbRef.child("chats").child(senderRoom!!).child("messages").push()
                .setValue(messageObject).addOnSuccessListener {
                    mDbRef.child("chats").child(receiverRoom!!).child("messages").push()
                        .setValue(messageObject)
                }

            messageBox.setText("")
        }
    }
}