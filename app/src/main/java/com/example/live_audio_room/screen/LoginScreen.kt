package com.example.live_audio_room

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.live_audio_room.authViewModel.AuthViewModel

// Define colors used in the design
val BackgroundColor = Color(0xFFFAF6F1)   // Soft Ivory
val InputBackground = Color(0xFFF5F0EB)   // Light Cream
val InputBorder = Color(0xFFD8D3CD)       // Pale Gray
val ButtonColor = Color(0xFFD2BFA3)       // Beige / Latte
val ButtonTextColor = Color(0xFF3A2F22)   // Dark Brown
val TextColor = Color(0xFF000000)         // Jet Black

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(modifier: Modifier = Modifier ,navController: NavHostController, authViewModel: AuthViewModel= viewModel()) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false)}
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "Login",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = TextColor
            )

            // Email TextField
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email", color = TextColor.copy(alpha = 0.6f)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = InputBackground,
                    unfocusedBorderColor = InputBorder,
                    focusedBorderColor = ButtonColor,
                    focusedTextColor = TextColor,
                    unfocusedLabelColor = TextColor
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            // Password TextField
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password", color = TextColor.copy(alpha = 0.6f)) },
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = InputBackground,
                    unfocusedBorderColor = InputBorder,
                    focusedBorderColor = ButtonColor,
//                    textColor = TextColor
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Forgot password?",
                color = TextColor.copy(alpha = 0.6f),
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.End)
            )

            // Login Button
            Button(
                onClick = {
                    isLoading = true // disable button immediately
                    authViewModel.login(email, password) { success, errorMessage ->
                        if (success) {
                            isLoading = false
                            navController.navigate("home") {
                                popUpTo("login") {
                                    inclusive = true
                                }
                            }
                        } else {
                            isLoading = false
                            AppUtil.showToast(context, errorMessage ?: "Something went wrong")
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = ButtonColor),
                enabled = !isLoading, // disables while loading
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Log in", color = ButtonTextColor, fontSize = 16.sp)
            }
        }
    }
}

//@Preview(showBackground = true , showSystemUi = true)
//@Composable
//fun loginPreview() {
//    login()
//}