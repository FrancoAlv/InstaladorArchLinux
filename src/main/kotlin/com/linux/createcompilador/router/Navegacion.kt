package com.linux.createcompilador.router

sealed class Navegacion(val titulo: String, val url: String, val siguiente: Navegacion? = null) {

    object Idioma : Navegacion("SELECIONA TU IDIOMA", "idioma", Teclado)
    object Teclado : Navegacion("SELECIONA TU TECLADo", "teclado", ZonaHoraria)
    object ZonaHoraria : Navegacion("ZonaHoraria", "ZonaHoraria", Instalacion)

    object Instalacion : Navegacion("ZonaHoraria", "ZonaHoraria", Procedimiento)
    object Procedimiento : Navegacion("ZonaHoraria", "ZonaHoraria", Cuenta)
    object Cuenta : Navegacion("ZonaHoraria", "ZonaHoraria", Personalizacion)
    object Personalizacion : Navegacion("ZonaHoraria", "ZonaHoraria", Finalizar)
    object Finalizar : Navegacion("ZonaHoraria", "ZonaHoraria")

    companion object {
        fun listNavegacion(): List<Navegacion> {
            return listOf(
                Idioma,
                Teclado,
                ZonaHoraria,
                Instalacion,
                Procedimiento,
                Cuenta,
                Personalizacion,
                Finalizar
            )
        }
    }

}