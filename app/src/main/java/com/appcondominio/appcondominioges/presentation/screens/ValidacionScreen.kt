package com.appcondominio.appcondominioges.presentation.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
fun ValidacionScreen(usuario: Usuario = Usuario(
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
            .padding(16.dp)
    ) {
        Text(
            text = "Pantalla de Validacion",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary

        )

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ValidacionScreenPreview() {
    ValidacionScreen()
}
