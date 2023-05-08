package com.linux.createcompilador.hexagonal.zonaHoraria.domain.data

data class ZonaHorariaSelect(
    var nombre: String? = null,
    var coordenadas: LatLng? = null,
    var isSelecionado: Boolean = false
)