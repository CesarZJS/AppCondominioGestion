package com.appcondominio.appcondominioges.domain.viewmodels

import com.appcondominio.appcondominioges.domain.models.DashboardData

sealed class DashboardUiState {
    object Loading : DashboardUiState()
    data class Success(val data: DashboardData) : DashboardUiState()
    data class Error(val message: String) : DashboardUiState()
}