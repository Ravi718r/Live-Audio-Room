package com.example.live_audio_room.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ------------------------
// Sample Data
// ------------------------

data class Room(val title: String, val speakerCount: Int)

val sampleRooms = listOf(
    Room("Tech Talk", 12),
    Room("Study Vibes", 5),
    Room("Music Chill", 8),
    Room("Startup Pitch", 10),
    Room("Casual Chat", 4),
    Room("Late Night Talks", 7)
)

// ------------------------
// Theme Colors
// ------------------------

val BackgroundColor = Color(0xFFFFFBF2)
val CardColor = Color(0xFFFDF5E6)
val TextPrimary = Color(0xFF5A4A3F)

// ------------------------
// Main Section Composable
// ------------------------

@Composable
fun AvailableRoomsSection(
    modifier: Modifier = Modifier,
    rooms: List<Room> = sampleRooms
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(BackgroundColor)
    ) {
        Text(
            text = "Available Rooms",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            items(rooms) { room ->
                RoomCard(room = room)
            }
        }
    }
}

// ------------------------
// Individual Room Card
// ------------------------

@Composable
fun RoomCard(room: Room) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = room.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = TextPrimary
            )
            Text(
                text = "${room.speakerCount} speakers",
                fontSize = 14.sp,
                color = TextPrimary.copy(alpha = 0.7f)
            )
        }
    }
}
