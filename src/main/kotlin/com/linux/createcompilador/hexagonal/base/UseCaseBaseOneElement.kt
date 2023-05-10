package com.linux.createcompilador.hexagonal.base

interface UseCaseBaseOneElement<out T> {
    suspend operator  fun invoke():T
}