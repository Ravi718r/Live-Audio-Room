package com.example.live_audio_room.pages

import android.content.Intent
import kotlin.random.Random
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.live_audio_room.LiveAudioRoomActivity
import com.example.live_audio_room.component.AvailableRoomsSection
import com.example.live_audio_room.component.HeaderView
import com.example.live_audio_room.component.JoinRoomButton
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

@Composable
fun HomePage(modifier: Modifier = Modifier) {

    var username by remember {
        mutableStateOf("")
    }
    var roomId by remember {
        mutableStateOf("")
    }
    var uid by remember {
        mutableStateOf("")
    }
    var context = LocalContext.current

    LaunchedEffect(Unit) {
        Firebase.firestore.collection("users")
            .document(FirebaseAuth.getInstance().currentUser?.uid!!)
            .get().addOnCompleteListener(){
                username = it.result.get("name").toString()//.split("").get(0)
                uid = it.result.get("uid").toString()
            }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF6F1))
            .padding(16.dp)
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderView(modifier = Modifier.height(10.dp))
            Spacer(modifier = Modifier.height(18.dp))
            AvailableRoomsSection(modifier=Modifier.weight(1f))
            Spacer(modifier = Modifier.height(20.dp))
            JoinRoomButton(onClick = {
                roomId = generateRoomId()
                val intent = Intent(context , LiveAudioRoomActivity::class.java)
                intent.putExtra("userId" ,uid)
                intent.putExtra("username" ,username)
                intent.putExtra("roomId" ,roomId)
                intent.putExtra("isHost" ,true)
                context.startActivity(intent)



            }, modifier = Modifier.padding(top = 8.dp))


        }
    }
}



fun generateRoomId(): String {
    val id = StringBuilder()
    while (id.length < 5) {
        val random = Random.nextInt(10) // Generates 0 to 9
        id.append(random)
    }
    return id.toString()
}
