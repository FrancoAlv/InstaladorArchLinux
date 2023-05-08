package com.linux.createcompilador.hexagonal.idioma.domain.data

import com.google.gson.annotations.SerializedName

data class IdiomaEntity(
    @field:SerializedName("id")
    val id: String? = null,
    @field:SerializedName("nombre")
    val nombre: String? = null,
    @field:SerializedName("check")
    val check: Boolean? = null
)








