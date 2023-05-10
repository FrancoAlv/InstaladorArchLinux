package com.linux.createcompilador.hexagonal.base

interface UseCaseBase<T,K> {
    suspend operator  fun invoke(data:T):K

}