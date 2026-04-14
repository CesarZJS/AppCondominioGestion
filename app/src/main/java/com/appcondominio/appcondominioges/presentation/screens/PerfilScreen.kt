package com.appcondominio.appcondominioges.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appcondominio.appcondominioges.domain.models.BotonMenu
import com.appcondominio.appcondominioges.domain.models.Usuario


@Composable
fun PerfilScreen(usuario: Usuario = Usuario(
    id = "1",
    nombre = "Usuario",
    email = "correo@ejemplo.com",
    rol = "rol",
    departamento = "departamento",
    token = "123",
    menu = listOf(
        BotonMenu("1", "Inicio", "home", "dashboard", 1),
        BotonMenu("1", "Inicio", "home", "dashboard", 1),
        BotonMenu("1", "Inicio", "home", "dashboard", 1),
    )
)) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)

        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Avatar
                Surface(
                    modifier = Modifier.size(100.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = usuario.nombre.take(1).uppercase(),
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = usuario.nombre,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = usuario.rol.uppercase(),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(24.dp))

                Divider()

                // Información del usuario
                PerfilInfoRow("Email", usuario.email)
                PerfilInfoRow("Departamento", usuario.departamento)
                PerfilInfoRow("ID de usuario", usuario.id)
            }
        }
    }
}

@Composable
fun PerfilInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = value,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PerfilScreenPreview() {
    PerfilScreen()
}
