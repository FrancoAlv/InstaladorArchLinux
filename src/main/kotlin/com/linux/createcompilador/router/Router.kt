package com.linux.createcompilador.router

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.linux.createcompilador.hexagonal.cuenta.infrastructure.view.Cuenta
import com.linux.createcompilador.hexagonal.finalizar.infrastructure.view.Finalizar
import com.linux.createcompilador.theme.gris
import com.linux.createcompilador.theme.primary
import com.linux.createcompilador.hexagonal.idioma.infrastructure.view.IdiomaComposable
import com.linux.createcompilador.hexagonal.instalacion.infrastructure.view.Instalacion
import com.linux.createcompilador.hexagonal.personalizacion.infrastructure.view.Personalizacion
import com.linux.createcompilador.hexagonal.procedimiento.infrastructure.view.Procedimiento
import com.linux.createcompilador.hexagonal.teclado.infrastructure.view.Teclado
import com.linux.createcompilador.hexagonal.zonaHoraria.infrastructure.view.ZonaHoraria
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
@Preview
fun Router() {

    val controlador = remember { Controller.state }
    val collectAsState by controlador.collectAsState(Dispatchers.IO)
    val corrutina = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {

        Column(modifier = Modifier.fillMaxWidth().align(Alignment.Center)) {
            when (collectAsState) {
                is Navegacion.Idioma -> {
                    IdiomaComposable()
                }

                is Navegacion.Teclado -> {
                    Teclado()
                }

                is Navegacion.Cuenta -> {
                    Cuenta()
                }

                is Navegacion.ZonaHoraria -> {
                    ZonaHoraria()
                }

                is Navegacion.Instalacion -> {
                    Instalacion()
                }

                is Navegacion.Procedimiento -> {
                    Procedimiento()
                }

                is Navegacion.Personalizacion -> {
                    Personalizacion()
                }

                is Navegacion.Finalizar -> {
                    Finalizar()
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button({
                corrutina.launch {
                    Controller.pop()
                }
            }, enabled = Navegacion.Idioma != collectAsState) {
                Text("Anterior")
            }
            Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Navegacion.listNavegacion().forEach {
                        Spacer(
                            Modifier.align(Alignment.CenterVertically).padding(horizontal = 5.dp).size(8.dp)
                                .clip(CircleShape).background(
                                if (collectAsState == it) primary else
                                    gris
                            )
                        )

                    }
                }
            }
            Button({
                corrutina.launch {
                    collectAsState.siguiente?.let {
                        Controller.plus(it)
                    }
                }
            }, enabled = collectAsState.siguiente != null) {
                Text("Siguiente")
            }
        }
    }


}