package com.appcondominio.appcondominioges.domain.usecases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appcondominio.appcondominioges.data.repositories.DashboardRepositorySimulado
import com.appcondominio.appcondominioges.domain.models.Usuario
import com.appcondominio.appcondominioges.domain.viewmodels.DashboardViewModel

class DashboardViewModelFactory(
    private val usuario: Usuario
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            val repository = DashboardRepositorySimulado()
            val useCase = GetDashboardUseCase(repository)
            return DashboardViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}