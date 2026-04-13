package com.appcondominio.appcondominioges.domain.models

data class BotonMenu(
    val id: String,
    val titulo: String,
    val icono: String,
    val ruta: String,
    val orden: Int
)
