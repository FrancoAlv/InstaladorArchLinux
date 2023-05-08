package com.linux.createcompilador.hexagonal.teclado.infrastructure.events

import com.linux.createcompilador.hexagonal.teclado.application.data.IdiomaTeclado

sealed class TecladoEvents {

    class onClick(val idioma: IdiomaTeclado): TecladoEvents()
    object Probar: TecladoEvents()


}