package com.linux.createcompilador.hexagonal.teclado.domain.preferent

import com.google.gson.reflect.TypeToken
import com.linux.createcompilador.hexagonal.teclado.application.data.IdiomaTeclado
import com.restau.proyect.base.localstorage.StorageService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy


@Repository
class TypeTecladoPreferent {

    @Value("\${app.secretKey}")
    lateinit var key: String

    lateinit var listpersonpre: StorageService<List<IdiomaTeclado>>

    lateinit var listperson: List<IdiomaTeclado>

    val filname = "keyboard"

    lateinit var tecladoPreferent: StorageService<IdiomaTeclado>

    val filnameselct = "keyselect"


    @PostConstruct
    private fun caragando() {
        listpersonpre =
            StorageService<List<IdiomaTeclado>>(object : TypeToken<List<IdiomaTeclado>>() {}, "lisIdioma", key)
        listpersonpre.load(filname)?.let {
            listperson = it
        } ?: run {
            listperson = listOf()
        }
        tecladoPreferent = StorageService<IdiomaTeclado>(object : TypeToken<IdiomaTeclado>() {}, "lisIdioma", key)

    }

    @PreDestroy
    fun delete() {
        tecladoPreferent.delete(filnameselct)
    }

    fun getlistTeclado(): List<IdiomaTeclado>? = listpersonpre.load(filname)


    fun saveteclado(value: IdiomaTeclado) {
        tecladoPreferent.save(value, filnameselct)
    }

    fun getteclado(): IdiomaTeclado? {
        return tecladoPreferent.load(filnameselct)
    }


}