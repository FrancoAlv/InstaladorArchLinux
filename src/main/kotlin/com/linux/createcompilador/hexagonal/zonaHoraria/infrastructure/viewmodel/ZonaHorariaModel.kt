package com.linux.createcompilador.hexagonal.zonaHoraria.infrastructure.viewmodel

import com.linux.createcompilador.hexagonal.base.ViewModelBase
import com.linux.createcompilador.hexagonal.zonaHoraria.application.GetListZonaHoraria
import com.linux.createcompilador.hexagonal.zonaHoraria.domain.data.ZonaHorariaSelect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import javax.annotation.PostConstruct


@Controller
class ZonaHorariaModel(
    @Autowired private val getListZonaHoraria: GetListZonaHoraria
) : ViewModelBase() {


    private val _listZonaHoraria = MutableStateFlow<List<ZonaHorariaSelect>>(listOf())

    val listZonaHoraria = _listZonaHoraria.asStateFlow()


    @PostConstruct
    fun init() {
        launch {
            _listZonaHoraria.value = getListZonaHoraria()
        }
    }


}