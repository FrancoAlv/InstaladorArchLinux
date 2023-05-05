package com.linux.createcompilador.viewmodel.teclado.events

import com.linux.createcompilador.data.IdiomaTeclado

sealed class TecladoEvents {

    class onClick(val idioma:IdiomaTeclado):TecladoEvents()
    object Probar:TecladoEvents()


}