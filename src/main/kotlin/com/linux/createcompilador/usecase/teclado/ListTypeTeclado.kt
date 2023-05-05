package com.linux.createcompilador.usecase.teclado

import com.linux.createcompilador.data.IdiomaTeclado
import com.linux.createcompilador.dominan.preferent.TypeTecladoPreferent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ListTypeTeclado (
    @Autowired private val typeTecladoPreferent:TypeTecladoPreferent
){

    operator fun invoke():List<IdiomaTeclado>{
        return typeTecladoPreferent.listperson
    }


}