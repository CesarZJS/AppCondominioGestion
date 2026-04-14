package com.appcondominio.appcondominioges.domain.usecases

import com.appcondominio.appcondominioges.domain.repositories.DashboardRepository
import com.appcondominio.appcondominioges.domain.models.DashboardData
import com.appcondominio.appcondominioges.domain.models.Usuario

class GetDashboardUseCase(
    private val repository: DashboardRepository
) {
    suspend operator fun invoke(usuario: Usuario): DashboardData {
        return repository.getDashboardData(usuario)
    }
}