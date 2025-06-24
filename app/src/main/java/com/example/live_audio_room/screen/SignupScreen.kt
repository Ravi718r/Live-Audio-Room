package com.example.live_audio_room.screen



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
import com.example.live_audio_room.AppUtil
import com.example.live_audio_room.authViewModel.AuthViewModel

// Reuse the color palette
val BackgroundColor = Color(0xFFFAF6F1)   // Soft Ivory
val InputBackground = Color(0xFFF5F0EB)   // Light Cream
val InputBorder = Color(0xFFD8D3CD)       // Pale Gray
val ButtonColor = Color(0xFFD2BFA3)       // Beige / Latte
val ButtonTextColor = Color(0xFF3A2F22)   // Dark Brown
val TextColor = Color(0xFF000000)         // Jet Black

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(modifier: Modifier=Modifier ,navController: NavHostController, authViewModel: AuthViewModel = viewModel()) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isLoading by remember {mutableStateOf(false) }
    var context = LocalContext.current


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
                text = "Sign up",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = TextColor
            )

            // Name field
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("Name", color = TextColor.copy(alpha = 0.6f)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = InputBackground,
                    unfocusedBorderColor = InputBorder,
                    focusedBorderColor = ButtonColor,
                    focusedTextColor = TextColor,
                    unfocusedTextColor = TextColor,
                    cursorColor = ButtonTextColor,
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            // Email field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email", color = TextColor.copy(alpha = 0.6f)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = InputBackground,
                    unfocusedBorderColor = InputBorder,
                    focusedBorderColor = ButtonColor,
                    focusedTextColor = TextColor,
                    unfocusedTextColor = TextColor,
                    cursorColor = ButtonTextColor,
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            // Password field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password", color = TextColor.copy(alpha = 0.6f)) },
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = InputBackground,
                    unfocusedBorderColor = InputBorder,
                    focusedBorderColor = ButtonColor,
                    focusedTextColor = TextColor,
                    unfocusedTextColor = TextColor,
                    cursorColor = ButtonTextColor,
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            // Sign up button
            Button(
                onClick = {

                    authViewModel.signup(name,email,password){success,errorMessage->
                        if(success){
                            // Handle successful signup
                            isLoading = true
                            navController.navigate("home"){
                                isLoading = false
                                popUpTo("login") {
                                    inclusive = true
                                }
                            }
                        }else{
                            // Handle signup failure
                            isLoading = true
                            AppUtil.showToast(context, errorMessage ?: "Something went wrong")
                        }
                    }
                }, enabled = !isLoading,
                colors = ButtonDefaults.buttonColors(containerColor = ButtonColor),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Sign up", color = ButtonTextColor, fontSize = 16.sp)
            }
        }
    }
}
