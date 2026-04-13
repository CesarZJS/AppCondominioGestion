package com.appcondominio.appcondominioges


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.appcondominio.appcondominioges.domain.models.Usuario
import com.appcondominio.appcondominioges.presentation.screens.MainScreen
import com.appcondominio.appcondominioges.presentation.screens.LoginScreen
import com.appcondominio.appcondominioges.ui.theme.AppCondominioGestionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppCondominioGestionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var usuarioLogueado by remember { mutableStateOf<Usuario?>(null) }

                    if (usuarioLogueado == null) {
                        LoginScreen { usuario ->
                            usuarioLogueado = usuario
                        }
                    } else {
                        MainScreen(
                            usuario = usuarioLogueado!!,
                            onCerrarSesion = {
                                usuarioLogueado = null
                            }
                        )
                    }
                }
            }
        }
    }
}