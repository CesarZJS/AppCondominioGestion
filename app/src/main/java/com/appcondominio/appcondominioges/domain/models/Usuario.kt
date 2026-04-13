package com.appcondominio.appcondominioges.domain.models

data class Usuario(
    val id: String,
    val nombre: String,
    val email: String,
    val rol: String,
    val departamento: String,
    val token: String,
    val menu: List<BotonMenu>
)
