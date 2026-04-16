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
            nombre = usuario.nombre,
            rol = usuario.rol,
            departamento = usuario.departamento,
            secciones = listOf(

                // 💰 ESTADO DE PAGO (principal)
                SeccionDashboard(
                    tipo = "pago",
                    titulo = "Estado de Pago",
                    datos = mapOf(
                        "Mes" to "Abril 2024",
                        "Monto" to "S/ 450.00",
                        "Estado" to "Pendiente",
                        "Vence" to "30 Abril",
                        "Último pago" to "01 Marzo"
                    ),
                    boton = BotonAccion("Pagar Ahora", "pagos")
                ),

                // 📊 RESUMEN (para mini cards)
                SeccionDashboard(
                    tipo = "estadistica",
                    titulo = "Resumen",
                    datos = mapOf(
                        "Pagado este mes" to "S/ 300",
                        "Multas" to "1",
                        "Pendientes" to "2",
                        "Total deuda" to "S/ 450"
                    ),
                    boton = null
                ),

                // 📅 RESERVAS
                SeccionDashboard(
                    tipo = "reserva",
                    titulo = "Mis Reservas",
                    datos = mapOf(
                        "Área" to "Parrilla",
                        "Fecha" to "20 Abril",
                        "Hora" to "6:00 PM",
                        "Estado" to "Confirmado"
                    ),
                    boton = BotonAccion("Reservar", "reservas")
                ),

                // ⚠️ MULTAS
                SeccionDashboard(
                    tipo = "accion",
                    titulo = "Multas",
                    datos = mapOf(
                        "1" to "Ruido excesivo - S/ 50",
                        "2" to "Estacionamiento indebido - S/ 30"
                    ),
                    boton = BotonAccion("Ver multas", "multas")
                ),

                // 📢 ANUNCIO
                SeccionDashboard(
                    tipo = "anuncio",
                    titulo = "Último Anuncio",
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
            nombre = usuario.nombre,
            rol = usuario.rol,
            departamento = usuario.departamento,
            secciones = listOf(
                SeccionDashboard(
                    tipo = "propiedad",
                    titulo = "Mis Propiedades",
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
            nombre = usuario.nombre,
            rol = usuario.rol,
            departamento = usuario.departamento,
            secciones = listOf(
                SeccionDashboard(
                    tipo = "estadistica",
                    titulo = "Estadísticas",
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