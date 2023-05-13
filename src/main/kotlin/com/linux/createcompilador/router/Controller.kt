package com.linux.createcompilador.router

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*


object Controller {
    private val _state = MutableStateFlow<Navegacion>(Navegacion.Instalacion)
    val state = _state.asStateFlow()
    private val stack = Stack<Navegacion>()
    private val _cambio = MutableSharedFlow<Boolean>()
    val cambio = _cambio.asSharedFlow()
    private const val TIME_TRANSICION = 0L


    init {
        stack.push(Navegacion.Instalacion)
    }


    suspend fun plus(string: Navegacion) {
        _cambio.emit(false)
        delay(TIME_TRANSICION)
        _state.emit(string)
        stack.push(string)
        _cambio.emit(true)
    }

    suspend fun pop() {
        _cambio.emit(false)
        delay(TIME_TRANSICION)
        if (!stack.empty() && stack.size > 1)
            stack.pop()
        _state.emit(stack.peek())
        _cambio.emit(true)
    }

    suspend fun clear() {
        _cambio.emit(false)
        delay(TIME_TRANSICION)
        val element = stack.firstElement()
        stack.clear()
        stack.push(element)
        _state.emit(element)
        _cambio.emit(true)
    }

    suspend fun clear(inicio: Navegacion) {
        _cambio.emit(false)
        stack.clear()
        stack.push(inicio)
        _state.emit(inicio)
        _cambio.emit(true)
    }


    suspend fun replace(element: Navegacion) {
        _cambio.emit(false)
        delay(TIME_TRANSICION)
        stack.pop()
        stack.push(element)
        _state.emit(element)
        _cambio.emit(true)
    }


    fun lastElement(): Navegacion {
        return stack.lastElement()
    }

}