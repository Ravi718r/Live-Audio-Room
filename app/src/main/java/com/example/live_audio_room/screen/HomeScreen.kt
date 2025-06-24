package com.example.live_audio_room.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.live_audio_room.pages.HomePage
import com.example.live_audio_room.pages.JoinPage
import com.example.live_audio_room.pages.JoinPage
import com.example.live_audio_room.pages.ProfilePage

// Define the soft theme colors
val SoftIvory = Color(0xFFFAF6F1)
val LightCream = Color(0xFFF5F0EB)
val Latte = Color(0xFFD2BFA3)
val ButtonTextColor1 = Color(0xFF3A2F22)

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    val navItems = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("History", Icons.Default.Info),
        NavItem("Profile", Icons.Default.Person),
    )

    var selectedIndex by rememberSaveable { mutableStateOf(0) }

    Scaffold(
        containerColor = SoftIvory,
        bottomBar = {
            BottomNavigationBar(navItems, selectedIndex) { index ->
                selectedIndex = index
            }
        }
    ) { innerPadding ->
        ContentScreen(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            selectedIndex
        )
    }
}

@Composable
fun BottomNavigationBar(
    items: List<NavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Card(
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(containerColor = LightCream),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, item ->
                    Box(
                        modifier = Modifier
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            IconButton(
                                onClick = { onItemSelected(index) },
                                modifier = Modifier.size(48.dp)
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label,
                                    tint = ButtonTextColor
                                )
                            }
                            Text(
                                text = item.label,
                                color = ButtonTextColor,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex: Int) {
    when (selectedIndex) {
        0 -> HomePage(modifier)
        1 -> JoinPage(modifier)
        2 -> ProfilePage(modifier)
    }
}

data class NavItem(
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)
