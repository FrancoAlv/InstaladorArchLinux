package com.linux.createcompilador.hexagonal.zonaHoraria.infrastructure.view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.linux.createcompilador.IOD.viewmodels
import com.linux.createcompilador.components.AutoTexfiled.AutoCompleteTextField
import com.linux.createcompilador.components.AutoTexfiled.ParamsData
import com.linux.createcompilador.hexagonal.zonaHoraria.domain.data.LatLng
import com.linux.createcompilador.hexagonal.zonaHoraria.domain.data.ZonaHorariaSelect
import com.linux.createcompilador.hexagonal.zonaHoraria.infrastructure.view.components.MapWithMarkers
import com.linux.createcompilador.hexagonal.zonaHoraria.infrastructure.viewmodel.ZonaHorariaModel


@Composable
@Preview
fun ZonaHoraria() {
    val viewModelBase:ZonaHorariaModel by viewmodels()
    val list by viewModelBase.listZonaHoraria.collectAsState()
    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
        AutoCompleteTextField(params = ParamsData(
            list,{},modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth(0.5f).padding(20.dp),
            "Busca Tu Zona Horaria",null,ZonaHorariaSelect::nombre, trailingIcon ={
                Icon(Icons.Outlined.Search, "")
            }))

/*
        val topLeft = LatLng(latitude =84.764846, -160.247162)
        val bottomRight = LatLng(latitude =-74.415114, 179.815592)
        val markers = listOf(
            LatLng(latitude = -5.184305, longitude = -80.663441),
            LatLng(-18.420506, 44.979654),
            LatLng(39.146662, -109.244490),
            topLeft,
            bottomRight
        )

        MapWithMarkers(
            mapImageResource = "dragables/bg.png",
            markers = markers,
            Modifier.align(Alignment.CenterHorizontally),
            topLeft, bottomRight
        )*/
    }

}

