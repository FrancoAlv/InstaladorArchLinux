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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Composable
@Preview
fun Router() {

    val controlador = remember { Controller.state }
    val collectAsState by controlador.collectAsState(Dispatchers.IO)
    val corrutina= rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {

        Column(modifier = Modifier.fillMaxWidth().align(Alignment.Center)) {
            when (collectAsState) {
                is Navegacion.Idioma -> {
                    Text("hola1")
                }
                is Navegacion.Teclado->{
                    Text("hola2     ")
                }
                is Navegacion.Cuenta->{

                }
                is Navegacion.ZonaHoraria->{

                }
                is Navegacion.Instalacion->{

                }
                is Navegacion.Procedimiento->{

                }
                is Navegacion.Personalizacion->{

                }
                is Navegacion.Finalizar->{

                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button({
                corrutina.launch {
                       Controller.pop()
                   }
            }, enabled = Navegacion.Idioma != collectAsState) {
                Text("Anterior")
            }
            Navegacion.listNavegacion().forEach {
                Spacer(
                    Modifier.align(Alignment.CenterVertically).size(10.dp).clip(CircleShape).background(
                        if (collectAsState == it) Color.Blue else
                            Color.Black
                    )
                )

            }
            Button({
                corrutina.launch {
                    collectAsState.siguiente?.let {
                        Controller.plus(it)
                    }
                }
            }, enabled = collectAsState.siguiente!=null) {
                Text("Siguiente")
            }
        }
    }


}