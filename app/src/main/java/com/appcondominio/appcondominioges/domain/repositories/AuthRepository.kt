package com.appcondominio.appcondominioges.domain.repositories

import com.appcondominio.appcondominioges.domain.models.Usuario

interface AuthRepository {
    suspend fun login(email:String, password: String): Result<Usuario>
}