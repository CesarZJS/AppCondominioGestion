package com.appcondominio.appcondominioges.data.repositories

import com.appcondominio.appcondominioges.domain.models.BotonMenu
import com.appcondominio.appcondominioges.domain.models.Usuario
import com.appcondominio.appcondominioges.domain.repositories.AuthRepository
import kotlinx.coroutines.delay

class AuthRepositorySimulado : AuthRepository {

    override suspend fun login(rol: String, password: String): Result<Usuario> {
        delay(1500)

        return when {
            rol == "inquilino" && password == "123" -> {
                Result.success(crearUsuarioInquilino())
            }
            rol == "propietario" && password == "123" -> {
                Result.success(crearUsuarioPropietario())
            }
            rol == "presidente" && password == "123" -> {
                Result.success(crearUsuarioPresidente())
            }
            else -> {
                Result.failure(Exception("Email o contraseña incorrectos"))
            }
        }
    }

    private fun crearUsuarioInquilino(): Usuario {
        return Usuario(
            id = "1",
            nombre = "Carlos Gómez",
            email = "inquilino@test.com",
            rol = "inquilino",
            departamento = "301",
            token = "token_falso_123",
            menu = listOf(
                BotonMenu("1", "Inicio", "home", "dashboard", 1),
                BotonMenu("2", "Pagos", "payment", "pagos", 2),
                BotonMenu("3", "Reserva", "home", "reserva", 3)
//                BotonMenu("4", "Mantenimiento", "person", "mantenimiento", 4),
//                BotonMenu("4", "Mantenimiento", "person", "mantenimiento", 4),
//                BotonMenu("3", "Configuracion", "campaign", "configuracion", 3),
//                BotonMenu("4", "Mantenimiento", "person", "mantenimiento", 4),
//                BotonMenu("5", "Validacion", "home", "validacion", 3),

            )
        )
    }

    private fun crearUsuarioPropietario(): Usuario {
        return Usuario(
            id = "2",
            nombre = "María Rodríguez",
            email = "propietario@test.com",
            rol = "propietario",
            departamento = "402",
            token = "token_falso_456",
            menu = listOf(
                BotonMenu("1", "Inicio", "home", "dashboard", 1),
                BotonMenu("2", "Pagos", "payment", "pagos", 2),
                BotonMenu("3", "Reserva", "home", "reserva", 3),
                BotonMenu("4", "Comunidcados", "home", "comunicados", 4)
            )
        )
    }

    private fun crearUsuarioPresidente(): Usuario {
        return Usuario(
            id = "3",
            nombre = "Juan Pérez",
            email = "presidente@test.com",
            rol = "presidente",
            departamento = "PH",
            token = "token_falso_789",
            menu = listOf(
                BotonMenu("1", "Inicio", "home", "dashboard", 1),
                BotonMenu("2", "Pagos", "payment", "pagos", 2),
                BotonMenu("3", "Reserva", "home", "reserva", 3),
                BotonMenu("4", "Comunidcados", "home", "comunicados", 4)
            )
        )
    }
}