package com.appcondominio.appcondominioges.presentation.navigation

sealed class Destinos(val route: String, val titulo: String) {
    object Dashboard : Destinos("dashboard", "Inicio")
    object Pagos : Destinos("pagos", "Pagos")
    object Configuracion : Destinos("configuracion", "Configuracion")

    object Mantenimiento : Destinos("mantenimiento", "Mantenimiento")

    object Validacion : Destinos("validacion", "Validacion")

    object Reservas : Destinos("reserva", "Reserva")

    object Comunicados : Destinos("comunicados", "Comunicados")
    object Perfil : Destinos("perfil", "Perfil")
}