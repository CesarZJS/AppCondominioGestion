package com.appcondominio.appcondominioges.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appcondominio.appcondominioges.domain.models.Usuario

@Composable
fun DashboardScreen(usuario: Usuario) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "¡Bienvenido, ${usuario.nombre}!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Rol: ${usuario.rol.uppercase()}",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("📊 Resumen", fontWeight = FontWeight.Bold)
                Text("Departamento: ${usuario.departamento}")
                Text("Email: ${usuario.email}")
            }
        }
    }
}