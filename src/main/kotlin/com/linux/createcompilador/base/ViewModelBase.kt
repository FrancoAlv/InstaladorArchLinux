package com.linux.createcompilador.base

import kotlinx.coroutines.*
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

abstract class ViewModelBase {

    private val exptionhandle = CoroutineExceptionHandler { _, throwable ->
        println("Error dectetado de en el $throwable ")
    }

    protected var lyceviewmodel = CoroutineScope(SupervisorJob() + exptionhandle)

    @PostConstruct
    fun postconstructor() {
        lyceviewmodel = CoroutineScope(SupervisorJob() + exptionhandle)
    }


    @PreDestroy
    fun destroy() {
        lyceviewmodel.cancel()
    }

    protected fun launch(onProcess: suspend () -> Unit): Job {
        return lyceviewmodel.launch(Dispatchers.IO + NonCancellable) { onProcess() }
    }


}