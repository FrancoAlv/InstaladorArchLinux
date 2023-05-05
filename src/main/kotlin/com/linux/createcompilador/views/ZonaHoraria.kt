package com.linux.createcompilador.views

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


import kotlinx.datetime.TimeZone


@Composable
@Preview
fun ZonaHoraria() {
    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {

        OutlinedTextField("", {

        }, label = {
            Text("Buscar una Ciudad")
        }, leadingIcon = {
            Icon(Icons.Outlined.Search, "")
        }, modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth(0.5f).padding(20.dp))
        Image(painterResource("dragables/bg.png"), "", modifier = Modifier.fillMaxWidth())

    }

}


fun getAllTimeZones(): List<String> {
    return listOf()
}