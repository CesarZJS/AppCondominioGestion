package com.appcondominio.appcondominioges.presentation.navigation

sealed class Destinos(val route: String, val titulo: String) {
    object Dashboard : Destinos("dashboard", "Inicio")
    object Pagos : Destinos("pagos", "Pagos")
    object Anuncios : Destinos("anuncios", "Anuncios")
    object Perfil : Destinos("perfil", "Perfil")
    object Propiedades : Destinos("propiedades", "Propiedades")
    object Cobros : Destinos("cobros", "Cobros")
    object Reservas : Destinos("reservas", "Reservas")
    object Vecinos : Destinos("vecinos", "Vecinos")
    object Reportes : Destinos("reportes", "Reportes")
    object Caja : Destinos("caja", "Caja Chica")
}