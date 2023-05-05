package com.linux.createcompilador.views

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.linux.createcompilador.components.CajaScroll


@Composable
@Preview
fun Teclado() {

    Column(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth(0.8f).padding(20.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text("Eliga la distribucion de teclado", modifier = Modifier.align(Alignment.CenterVertically))
            Button({

            }) {
                Text("Probar")
            }
        }
        Column (modifier = Modifier.fillMaxWidth().fillMaxHeight()){
            CajaScroll(heightbox = 0.6f) {

            }
            OutlinedTextField("", {}, label = {
                Text("Escriba aqui para probar el teclado")
            }, modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth(0.8f))
        }


    }

}