package com.appcondominio.appcondominioges.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appcondominio.appcondominioges.domain.models.DashboardData
import com.appcondominio.appcondominioges.domain.models.Usuario
import com.appcondominio.appcondominioges.domain.usecases.GetDashboardUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val getDashboardUseCase: GetDashboardUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val uiState: StateFlow<DashboardUiState> = _uiState

    fun loadDashboard(usuario: Usuario) {
        viewModelScope.launch {
            _uiState.value = DashboardUiState.Loading
            try {
                val data = getDashboardUseCase(usuario)
                _uiState.value = DashboardUiState.Success(data)
            } catch (e: Exception) {
                _uiState.value = DashboardUiState.Error(e.message ?: "Error al cargar")
            }
        }
    }
}
