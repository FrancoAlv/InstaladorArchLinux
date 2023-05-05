package com.linux.createcompilador.viewmodel.teclado

import com.linux.createcompilador.base.ViewModelBase
import com.linux.createcompilador.data.IdiomaTeclado
import com.linux.createcompilador.usecase.teclado.ListTypeTeclado
import com.linux.createcompilador.usecase.teclado.ProbrarTeclado
import com.linux.createcompilador.usecase.teclado.SelectTeclado
import com.linux.createcompilador.viewmodel.teclado.events.TecladoEvents
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import javax.annotation.PostConstruct

@Controller
class TecladoModel(
    @Autowired private val listTypeTeclado: ListTypeTeclado,
    @Autowired private val selectTeclado:SelectTeclado,
    @Autowired private val probrarTeclado:ProbrarTeclado
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