package com.linux.createcompilador.hexagonal.teclado.infrastructure.viewmodel

import com.linux.createcompilador.hexagonal.base.ViewModelBase
import com.linux.createcompilador.hexagonal.teclado.application.data.IdiomaTeclado
import com.linux.createcompilador.hexagonal.teclado.application.usecase.ListTypeTeclado
import com.linux.createcompilador.hexagonal.teclado.application.usecase.ProbrarTeclado
import com.linux.createcompilador.hexagonal.teclado.application.usecase.SelectTeclado
import com.linux.createcompilador.hexagonal.teclado.infrastructure.events.TecladoEvents
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Controller
import javax.annotation.PostConstruct

@Controller
@Lazy
@Scope("singleton")
class TecladoModel(
    @Autowired private val listTypeTeclado: ListTypeTeclado,
    @Autowired private val selectTeclado: SelectTeclado,
    @Autowired private val probrarTeclado: ProbrarTeclado
) : ViewModelBase() {

    private val _listTeclado = MutableStateFlow<List<IdiomaTeclado>>(listOf())
    val listTeclado = _listTeclado.asStateFlow()

    private val events = MutableStateFlow<TecladoEvents?>(null)

    @PostConstruct
    fun init() {
        launch {
            _listTeclado.value = listTypeTeclado()
        }
        launch {
            events.filterNotNull().collect {
                when (it) {
                    is TecladoEvents.onClick -> {
                        _listTeclado.value=selectTeclado(it.idioma)
                    }
                    is TecladoEvents.Probar -> {
                        probrarTeclado()
                    }
                }
            }
        }
    }


    fun emit(value: TecladoEvents) {
        events.value = value
    }


}