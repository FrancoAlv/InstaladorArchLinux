package com.linux.createcompilador.hexagonal.teclado.infrastructure.view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.linux.createcompilador.IOD.viewmodels
import com.linux.createcompilador.components.CajaScroll
import com.linux.createcompilador.hexagonal.teclado.infrastructure.viewmodel.TecladoModel
import com.linux.createcompilador.hexagonal.teclado.infrastructure.events.TecladoEvents


@Composable
@Preview
fun Teclado() {

    val tecladoModel: TecladoModel by viewmodels()
    val listTeclado by tecladoModel.listTeclado.collectAsState()
    var textoprueba by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth(0.8f).padding(20.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text("Eliga la distribucion de teclado", modifier = Modifier.align(Alignment.CenterVertically))
            Button({
                tecladoModel.emit(TecladoEvents.Probar)
            }) {
                Text("Probar")
            }
        }
        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
            CajaScroll(heightbox = 0.6f) {
                items(listTeclado) {
                    Row(
                        modifier = Modifier.fillMaxWidth().clickable {
                            tecladoModel.emit(TecladoEvents.onClick(it))
                        },
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(it.titulo ?: "", modifier = Modifier)
                            Text(it.subtitulo ?: "", modifier = Modifier)
                        }
                        if (it.isSelcionado)
                            Icon(Icons.Outlined.Check, "")
                    }
                }
            }
            OutlinedTextField(textoprueba, {
                textoprueba = it
            }, label = {
                Text("Escriba aqui para probar el teclado")
            }, modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth(0.8f))
        }


    }

}