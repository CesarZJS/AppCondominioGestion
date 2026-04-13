package com.appcondominio.appcondominioges.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.*
import com.appcondominio.appcondominioges.domain.models.BotonMenu
import com.appcondominio.appcondominioges.domain.models.Usuario
import com.appcondominio.appcondominioges.presentation.navigation.Destinos
import com.appcondominio.appcondominioges.presentation.screens.DashboardScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    usuario: Usuario = Usuario(
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
    ),
    onCerrarSesion: () -> Unit
)
{
    val navController = rememberNavController()
    val menuItems = usuario.menu.sortedBy { it.orden }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(
                        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                    ) {
                        Text("Gestión")
                        Text(
                            text = "${usuario.rol.uppercase()} | Depto ${usuario.departamento}",
                            fontSize = 12.sp
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onCerrarSesion) {
                        Icon(Icons.Default.Home, contentDescription = "Cerrar Sesión")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                contentColor = Color.White
            ) {
                menuItems.forEach { item ->
                    val selected = navController.currentBackStackEntryAsState().value?.destination?.route == item.ruta

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(item.ruta) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = obtenerIcono(item.icono),
                                contentDescription = item.titulo
                            )
                        },
                        label = { Text(item.titulo, maxLines = 1, overflow = TextOverflow.Ellipsis, fontSize = 12.sp)    },
                        colors = NavigationBarItemDefaults.colors(
                            selectedTextColor = Color.Black,          // Color cuando está seleccionado
                            unselectedTextColor = Color.LightGray,    // Color cuando no está seleccionado
                            indicatorColor = Color(0xFF3F51B5)        // Color de la "burbuja" de selección
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).background(
            Brush .verticalGradient(
                colors = listOf(
                    Color.White,
                    Color(0xFF283593)
                )
            )
        )) {
            NavHost(
                navController = navController,
                startDestination = menuItems.first().ruta

            ) {
                composable(Destinos.Dashboard.route) {
                    DashboardScreen(usuario)
                }
                composable(Destinos.Pagos.route) {
                    PagosScreen(usuario)
                }
                composable(Destinos.Configuracion.route) {
                    ConfiguracionScreen(usuario)
                }
                composable(Destinos.Mantenimiento.route) {
                    MantenimientoScreen(usuario)
                }
                composable(Destinos.Validacion.route) {
                    ValidacionScreen(usuario)
                }
                composable(Destinos.Perfil.route) {
                    Text("Pantalla de Perfil")
                }
                composable(Destinos.Reservas.route) {
                    ReservasScreen(usuario)
                }
                composable(Destinos.Comunicados.route) {
                    ComunicadosScreen(usuario)
                }
                composable(Destinos.Reportes.route) {
                    Text("Pantalla de Reportes")
                }
                composable(Destinos.Caja.route) {
                    Text("Pantalla de Caja Chica")
                }
            }
        }
    }
}

fun obtenerIcono(nombre: String): ImageVector {
    return when (nombre) {
        "home" -> Icons.Default.Home
        "person" -> Icons.Default.Person
        "logout" -> Icons.Default.Person
        "email" -> Icons.Default.Email
        "lock" -> Icons.Default.Lock
        "add" -> Icons.Default.Add
        "delete" -> Icons.Default.Delete
        "edit" -> Icons.Default.Edit
        "search" -> Icons.Default.Search
        "menu" -> Icons.Default.Menu
        // Los que no tengo, uso Home
        else -> Icons.Default.Home
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen(onCerrarSesion = { })}