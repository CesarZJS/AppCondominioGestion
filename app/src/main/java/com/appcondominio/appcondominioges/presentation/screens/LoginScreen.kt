package com.appcondominio.appcondominioges.presentation.screens

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appcondominio.appcondominioges.data.repositories.AuthRepositorySimulado
import com.appcondominio.appcondominioges.domain.models.Usuario
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onLoginExitoso: (Usuario) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val authRepository = remember { AuthRepositorySimulado() }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush .verticalGradient(
                    colors = listOf(
                        Color(0xFF1A237E),
                        Color(0xFF283593)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp, 120.dp,40.dp, 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo
            Surface(
                modifier = Modifier.size(80.dp),
                shape = RoundedCornerShape(20.dp),
                color = Color.White.copy(alpha = 0.1f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Logo",
                        modifier = Modifier.size(50.dp),
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "CondominioGestión",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "Tu comunidad en un solo lugar",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Formulario
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Text(
                        text = "Iniciar Sesión",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A237E)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Usuario") },
                        placeholder = { Text("ejemplo: inquilino") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        leadingIcon = {
                            Icon(Icons.Default.Email, contentDescription = null)
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.Black, //texto cuando escribes
                                unfocusedTextColor = Color.DarkGray,  //texto cuando no está seleccionado

                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = {
                            Icon(Icons.Default.Lock, contentDescription = null)
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black, //texto cuando escribes
                            unfocusedTextColor = Color.DarkGray,  //texto cuando no está seleccionado

                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    if (errorMessage != null) {
                        Text(
                            text = errorMessage!!,
                            color = Color.Red,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    Button(
                        onClick = {
                            isLoading = true
                            errorMessage = null
                            scope.launch {
                                val result = authRepository.login(email, password)
                                result.fold(
                                    onSuccess = { usuario ->
                                        onLoginExitoso(usuario)
                                    },
                                    onFailure = { error ->
                                        errorMessage = error.message
                                        isLoading = false
                                    }
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        enabled = !isLoading,
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1A237E),
                            disabledContainerColor = Color(0xFF1A237E),
                            disabledContentColor = Color.White
                        )
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = Color.White,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text("Ingresar", fontSize = 16.sp)
                        }
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(onLoginExitoso = { usuario ->
        println("Login simulado para: ${usuario.nombre}")
    })
}