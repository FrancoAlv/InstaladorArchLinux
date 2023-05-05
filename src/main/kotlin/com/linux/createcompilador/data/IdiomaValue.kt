package com.linux.createcompilador.data

import com.google.gson.annotations.SerializedName


data class IdiomaValue(
    @field:SerializedName("key")
    val key: String? = null,
    @field:SerializedName("value")
    val value: String? = null,
)
