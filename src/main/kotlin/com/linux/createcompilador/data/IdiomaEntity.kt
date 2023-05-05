package com.linux.createcompilador.data

import com.google.gson.annotations.SerializedName

data class IdiomaEntity(
    @field:SerializedName("id")
    val id: String? = null,
    @field:SerializedName("nombre")
    val nombre: String? = null,
    @field:SerializedName("check")
    val check: Boolean? = null
)


data class IdiomaValue(
    @field:SerializedName("key")
    val key: String? = null,
    @field:SerializedName("value")
    val value: String? = null,
)


data class IdiomaTeclado(
    @field:SerializedName("id")
    val id: String? = null,
    @field:SerializedName("titulo")
    val titulo: String? = null,
    @field:SerializedName("subtitulo")
    val subtitulo: String? = null,
    @field:SerializedName("comando")
    val comando: String? = null,
)




