package com.linux.createcompilador.hexagonal.instalacion.infrastructure.view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.linux.createcompilador.components.AutoTexfiled.ParamsData
import com.linux.createcompilador.components.DropDownMenu
import com.linux.createcompilador.hexagonal.instalacion.domain.data.Disco


@Composable
@Preview
fun Instalacion() {

    Box(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
        Column(modifier = Modifier.align(Alignment.TopCenter).fillMaxWidth(0.5f)) {
            Row{
                Text("Selecciona tu disco Duro:", textAlign = TextAlign.Center, modifier = Modifier.fillMaxHeight())
                DropDownMenu(
                    ParamsData(
                        listOf(Disco("disco1"), Disco("disco2")),
                        atributo = Disco::nombre,
                        label = "",
                        onItemSelected = {

                        })
                )
            }
            Text("¿Qué te gustaría hacer?")
            Box(modifier = Modifier.border(BorderStroke(2.dp))) {
                Column {

                }
            }
        }
    }
}


