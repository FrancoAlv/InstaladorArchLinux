package com.linux.createcompilador.hexagonal.teclado.application.usecase

import com.linux.createcompilador.hexagonal.teclado.application.data.IdiomaTeclado
import com.linux.createcompilador.hexagonal.teclado.domain.preferent.TypeTecladoPreferent
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