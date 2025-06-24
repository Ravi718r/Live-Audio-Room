package com.example.live_audio_room.pages

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.live_audio_room.LiveAudioRoomActivity
import com.example.live_audio_room.component.JoinRoomButton
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlin.random.Random

@Composable
fun JoinPage(modifier: Modifier = Modifier) {
    val backgroundColor = Color(0xFFFAF6F1)

    var username by remember {
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
    val messages = listOf(
        "Welcome to the world of conversations!",
        "Join and connect with amazing people.",
        "Your voice matters — let's talk.",
        "Discover new topics and make friends."
    )
    val randomText = remember { messages.random() }

    var roomId by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Join a Room",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5A4A3F)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = randomText,
                fontSize = 16.sp,
                color = Color(0xFF5A4A3F).copy(alpha = 0.8f),
                modifier = Modifier.padding(horizontal = 8.dp),
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Non-editable Name field
            OutlinedTextField(
                value = username,
                onValueChange = {username=it}, // No-op to prevent editing
                label = { Text("Your Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            // Non-editable Name field
            OutlinedTextField(
                value = uid,
                onValueChange = {}, // No-op to prevent editing
                label = { Text("Your Name") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            OutlinedTextField(
                value = roomId,
                onValueChange = { roomId = it },
                label = { Text("Room ID") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            JoinRoomButton(
                onClick = {
                    val intent = Intent(context, LiveAudioRoomActivity::class.java)
                    intent.putExtra("userId", uid)
                    intent.putExtra("username", username)
                    intent.putExtra("roomId", roomId)
                    intent.putExtra("isHost", false)
                    context.startActivity(intent)
                },
                text = "Join Room",

            )

        }
    }
}
