package com.appcondominio.appcondominioges.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appcondominio.appcondominioges.domain.models.BotonMenu
import com.appcondominio.appcondominioges.domain.models.SeccionDashboard
import com.appcondominio.appcondominioges.domain.models.Usuario
import com.appcondominio.appcondominioges.domain.viewmodels.DashboardUiState
import com.appcondominio.appcondominioges.domain.viewmodels.DashboardViewModel
import com.appcondominio.appcondominioges.domain.usecases.DashboardViewModelFactory
import androidx.compose.foundation.lazy.items
@Composable
fun NotificacionesScreen(
    usuario: Usuario = Usuario(
        id = "1",
        nombre = "Usuario",
        email = "correo@ejemplo.com",
        rol = "inquilino",
        departamento = "301",
        token = "123",
        menu = listOf(
            BotonMenu("1", "Inicio", "home", "dashboard", 1),
        )
    )
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Home, null, tint = Color(0xFF2196F3))
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text("Notificacion 1", fontWeight = FontWeight.Bold)
                Text("titulo", fontSize = 12.sp, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))


        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Home, null, tint = Color(0xFF2196F3))
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text("Notificacion 2", fontWeight = FontWeight.Bold)
                Text("titulo", fontSize = 12.sp, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))


        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Home, null, tint = Color(0xFF2196F3))
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("Notificacion 3", fontWeight = FontWeight.Bold)
                Text("titulo", fontSize = 12.sp, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))


        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Home, null, tint = Color(0xFF2196F3))
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("Notificacion 4", fontWeight = FontWeight.Bold)
                Text("titulo", fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotificacionesScreenPreview() {
    NotificacionesScreen()
}