package com.appcondominio.appcondominioges.data.repositories

import com.appcondominio.appcondominioges.domain.models.*
import com.appcondominio.appcondominioges.domain.repositories.DashboardRepository

class DashboardRepositorySimulado : DashboardRepository {

    override suspend fun getDashboardData(usuario: Usuario): DashboardData {
        return when (usuario.rol.lowercase()) {
            "inquilino" -> getInquilinoDashboard(usuario)
            "propietario" -> getPropietarioDashboard(usuario)
            "presidente" -> getPresidenteDashboard(usuario)
            else -> getInquilinoDashboard(usuario)
        }
    }

    private fun getInquilinoDashboard(usuario: Usuario): DashboardData {
        return DashboardData(
            bienvenida = "¡Bienvenido!",
            nombre = usuario.nombre,
            rol = usuario.rol,
            departamento = usuario.departamento,
            secciones = listOf(
                SeccionDashboard(
                    tipo = "pago",
                    titulo = "Estado de Pago",
                    icono = "payment",
                    datos = mapOf(
                        "Mes" to "Abril 2024",
                        "Monto" to "S/ 450.00",
                        "Estado" to "Pendiente"
                    ),
                    boton = BotonAccion("Pagar Ahora", "pagos")
                ),
                SeccionDashboard(
                    tipo = "anuncio",
                    titulo = "Último Anuncio",
                    icono = "campaign",
                    datos = mapOf(
                        "titulo" to "Reunión de condominio",
                        "fecha" to "Sábado 20 de abril - 10:00 AM"
                    ),
                    boton = null
                )
            )
        )
    }

    private fun getPropietarioDashboard(usuario: Usuario): DashboardData {
        return DashboardData(
            bienvenida = "¡Bienvenido!",
            nombre = usuario.nombre,
            rol = usuario.rol,
            departamento = usuario.departamento,
            secciones = listOf(
                SeccionDashboard(
                    tipo = "propiedad",
                    titulo = "Mis Propiedades",
                    icono = "business",
                    datos = mapOf(
                        "Principal" to "Depto ${usuario.departamento}",
                        "Propiedad 2" to "Depto 401",
                        "Propiedad 3" to "Depto 402"
                    ),
                    boton = null
                ),
                SeccionDashboard(
                    tipo = "cobro",
                    titulo = "Resumen de Cobros",
                    icono = "attach_money",
                    datos = mapOf(
                        "Total a cobrar" to "S/ 1,200",
                        "Cobrado" to "S/ 800",
                        "Pendiente" to "S/ 400"
                    ),
                    boton = null
                )
            )
        )
    }

    private fun getPresidenteDashboard(usuario: Usuario): DashboardData {
        return DashboardData(
            bienvenida = "Panel de Control",
            nombre = usuario.nombre,
            rol = usuario.rol,
            departamento = usuario.departamento,
            secciones = listOf(
                SeccionDashboard(
                    tipo = "estadistica",
                    titulo = "Estadísticas",
                    icono = "bar_chart",
                    datos = mapOf(
                        "Vecinos" to "45",
                        "Caja" to "S/15K",
                        "Morosos" to "5"
                    ),
                    boton = null
                ),
                SeccionDashboard(
                    tipo = "accion",
                    titulo = "Acciones Pendientes",
                    icono = "warning",
                    datos = mapOf(
                        "item1" to "5 reservas por aprobar",
                        "item2" to "3 reportes de mantenimiento",
                        "item3" to "Asamblea por programar"
                    ),
                    boton = BotonAccion("Ver todas", "acciones")
                )
            )
        )
    }
}