package com.example.live_audio_room

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.zegocloud.uikit.prebuilt.liveaudioroom.ZegoUIKitPrebuiltLiveAudioRoomConfig
import com.zegocloud.uikit.prebuilt.liveaudioroom.ZegoUIKitPrebuiltLiveAudioRoomFragment

class LiveAudioRoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_live_audio_room)

        val roomId = intent.getStringExtra("roomId")
        val roomIdTextView = findViewById<TextView>(R.id.room_id_textview)
        roomIdTextView.text = "Room Id : $roomId"
        addFragment()
    }

    private fun addFragment() {
        val appID: Long = Constant.appId
        val appSign = Constant.appSign
        val userID = intent.getStringExtra("userId")
        val userName = intent.getStringExtra("username") ?: "Guest"

        val isHost = intent.getBooleanExtra("isHost", false)
        val roomID = intent.getStringExtra("roomId")

        val config = if (isHost) {
            ZegoUIKitPrebuiltLiveAudioRoomConfig.host()
        } else {
            ZegoUIKitPrebuiltLiveAudioRoomConfig.audience().apply {
                // Show "Request to Join" button
                takeSeatIndexWhenJoining = -1
            }

        }

        val fragment = ZegoUIKitPrebuiltLiveAudioRoomFragment.newInstance(
            appID, appSign, userID, userName, roomID, config
        )

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_Container, fragment)
            .commitNow()
    }


}
