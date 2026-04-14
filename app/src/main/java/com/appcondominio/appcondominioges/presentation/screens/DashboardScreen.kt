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
fun DashboardScreen(
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
    val viewModel: DashboardViewModel = viewModel(
        factory = DashboardViewModelFactory(usuario)
    )

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadDashboard(usuario)
    }

    when (uiState) {
        is DashboardUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is DashboardUiState.Success -> {
            val data = (uiState as DashboardUiState.Success).data
            DashboardContent(
                nombre = data.nombre,
                rol = data.rol,
                departamento = data.departamento,
                secciones = data.secciones
            )
        }
        is DashboardUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: ${(uiState as DashboardUiState.Error).message}")
            }
        }
    }
}

@Composable
fun DashboardContent(
    nombre: String,
    rol: String,
    departamento: String,
    secciones: List<SeccionDashboard>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TarjetaBienvenida(nombre = nombre, rol = rol, departamento = departamento)

        secciones.forEach { seccion ->
            when (seccion.tipo) {
                "pago" -> TarjetaPago(seccion)
                "anuncio" -> TarjetaAnuncio(seccion)
                "propiedad" -> TarjetaPropiedad(seccion)
                "cobro" -> TarjetaCobro(seccion)
                "estadistica" -> TarjetaEstadisticas(seccion)
                "accion" -> TarjetaAccion(seccion)
            }
        }
    }
}

@Composable
fun TarjetaBienvenida(nombre: String, rol: String, departamento: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A237E))
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text("¡Bienvenido!", fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f))
            Text(nombre, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text("${rol.uppercase()} | Depto $departamento", fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f))
        }
    }
}

@Composable
fun TarjetaPago(seccion: SeccionDashboard) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Home, null, tint = Color(0xFF4CAF50))
                Spacer(modifier = Modifier.width(8.dp))
                Text(seccion.titulo, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))
            seccion.datos.forEach { (key, value) ->
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text(key, color = Color.Gray)
                    Text(value, fontWeight = FontWeight.Bold, color = if (key == "Estado") Color(0xFFFF5722) else Color.Black)
                }
            }
            seccion.boton?.let {
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(it.texto, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun TarjetaAnuncio(seccion: SeccionDashboard) {
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
                Text(seccion.titulo, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(seccion.datos["titulo"] ?: "", fontWeight = FontWeight.Bold)
            Text(seccion.datos["fecha"] ?: "", fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Composable
fun TarjetaPropiedad(seccion: SeccionDashboard) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Home, null, tint = Color(0xFF1A237E))
                Spacer(modifier = Modifier.width(8.dp))
                Text(seccion.titulo, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))
            seccion.datos.forEach { (_, value) ->
                Text("🏢 $value")
            }
        }
    }
}

@Composable
fun TarjetaCobro(seccion: SeccionDashboard) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Home, null, tint = Color(0xFF4CAF50))
                Spacer(modifier = Modifier.width(8.dp))
                Text(seccion.titulo, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))
            seccion.datos.forEach { (key, value) ->
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text(key, color = Color.Gray)
                    Text(value, fontWeight = FontWeight.Bold, color = if (key == "Pendiente") Color(0xFFFF5722) else Color.Black)
                }
            }
        }
    }
}

@Composable
fun TarjetaEstadisticas(seccion: SeccionDashboard) {
    Log.d("pruebadashboard", "Tipo: ${seccion.tipo}")
    Log.d("pruebadashboard", "Título: ${seccion.titulo}")
    Log.d("pruebadashboard", "Datos: ${seccion.datos}")
    Log.d("pruebadashboard", "Cantidad de items: ${seccion.datos.size}")

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(seccion.titulo, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(seccion.datos.toList()) { (key, value) ->
                    TarjetaEstadisticaItem(key, value)
                }
            }
        }
    }
}

@Composable
fun TarjetaEstadisticaItem(label: String, valor: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(12.dp)) {
            Text(label, fontSize = 12.sp, color = Color.Gray)
            Text(valor, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A237E))
        }
    }
}

@Composable
fun TarjetaAccion(seccion: SeccionDashboard) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Warning, null, tint = Color(0xFFFF5722))
                Spacer(modifier = Modifier.width(8.dp))
                Text(seccion.titulo, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))
            seccion.datos.forEach { (_, value) ->
                Text("• $value")
            }
            seccion.boton?.let {
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(it.texto, color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}