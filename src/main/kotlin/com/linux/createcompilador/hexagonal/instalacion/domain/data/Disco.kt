package com.linux.createcompilador.hexagonal.instalacion.domain.data

import com.google.gson.annotations.SerializedName

data class Disco(
    @field:SerializedName("nombre")
    val nombre: String? = null,
    @field:SerializedName("espaciomb")
    val espaciomb: Float? = null,
    @field:SerializedName("ruta")
    val ruta: String? = null
)
