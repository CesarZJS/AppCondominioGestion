package com.appcondominio.appcondominioges.domain.models

data class DashboardData(
    val nombre: String,
    val rol: String,
    val departamento: String,
    val secciones: List<SeccionDashboard>
)

data class SeccionDashboard(
    val tipo: String,
    val titulo: String,
    val datos: Map<String, String>,
    val boton: BotonAccion?
)

data class BotonAccion(
    val texto: String,
    val ruta: String
)