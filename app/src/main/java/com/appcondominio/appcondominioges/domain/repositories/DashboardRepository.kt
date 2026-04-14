package com.appcondominio.appcondominioges.domain.repositories

import com.appcondominio.appcondominioges.domain.models.DashboardData
import com.appcondominio.appcondominioges.domain.models.Usuario

interface DashboardRepository {
    suspend fun getDashboardData(usuario: Usuario): DashboardData
}