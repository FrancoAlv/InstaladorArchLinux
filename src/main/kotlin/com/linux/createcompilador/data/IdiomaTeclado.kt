package com.linux.createcompilador.data

import com.google.gson.annotations.SerializedName


data class IdiomaTeclado(
    @field:SerializedName("id")
    val id: String? = null,
    @field:SerializedName("titulo")
    val titulo: String? = null,
    @field:SerializedName("subtitulo")
    val subtitulo: String? = null,
    @field:SerializedName("comando")
    val comando: String? = null,
    @Transient
    var isSelcionado:Boolean=false
)