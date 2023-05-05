package com.linux.createcompilador.usecase.teclado

import com.linux.createcompilador.data.IdiomaTeclado
import com.linux.createcompilador.dominan.preferent.TypeTecladoPreferent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SelectTeclado(
    @Autowired private val typeTecladoPreferent: TypeTecladoPreferent,

    ) {

    operator fun invoke(value: IdiomaTeclado): List<IdiomaTeclado> {
        typeTecladoPreferent.saveteclado(value)
        return typeTecladoPreferent.getlistTeclado()?.map {
            it.copy(isSelcionado = it.id == value.id)
        } ?: listOf()
    }

}