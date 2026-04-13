package com.appcondominio.appcondominioges.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.*
import com.appcondominio.appcondominioges.domain.models.Usuario
import com.appcondominio.appcondominioges.presentation.navigation.Destinos
import com.appcondominio.appcondominioges.presentation.screens.DashboardScreen

@OptIn(ExperimentalMaterial3Api::class) // 👈 Agrega esto para el TopAppBar
@Composable
fun MainScreen(
    usuario: Usuario,
    onCerrarSesion: () -> Unit
) {
    val navController = rememberNavController()
    val menuItems = usuario.menu.sortedBy { it.orden }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(  // 👈 Cambia a CenterAlignedTopAppBar
                title = {
                    Column(
                        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                    ) {
                        Text("App Condominio")
                        Text(
                            text = "${usuario.rol.uppercase()} | Depto ${usuario.departamento}",
                            fontSize = 12.sp
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onCerrarSesion) {
                        Icon(Icons.Default.Home, contentDescription = "Cerrar Sesión") // 👈 Cambia a Logout
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            NavigationBar {
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
                        label = { Text(item.titulo) }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(
                navController = navController,
                startDestination = menuItems.first().ruta
            ) {
                composable(Destinos.Dashboard.route) {
                    DashboardScreen(usuario)
                }
                composable(Destinos.Pagos.route) {
                    Text("Pantalla de Pagos")
                }
                composable(Destinos.Anuncios.route) {
                    Text("Pantalla de Anuncios")
                }
                composable(Destinos.Perfil.route) {
                    Text("Pantalla de Perfil")
                }
                composable(Destinos.Propiedades.route) {
                    Text("Pantalla de Propiedades")
                }
                composable(Destinos.Cobros.route) {
                    Text("Pantalla de Cobros")
                }
                composable(Destinos.Reservas.route) {
                    Text("Pantalla de Reservas")
                }
                composable(Destinos.Vecinos.route) {
                    Text("Pantalla de Vecinos")
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