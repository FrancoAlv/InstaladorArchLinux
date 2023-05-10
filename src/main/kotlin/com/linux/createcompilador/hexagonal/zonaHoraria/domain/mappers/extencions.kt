package com.linux.createcompilador.hexagonal.zonaHoraria.domain.mappers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform


fun <T> Flow<T>.repeated(times: Int): Flow<T> {
    val map = mutableMapOf<T, Int>()
    return transform { value ->
        val count = (map[value] ?: 0) + 1
        map[value] = count
        if (count >= times) {
            return@transform emit(value)
        }
    }
}