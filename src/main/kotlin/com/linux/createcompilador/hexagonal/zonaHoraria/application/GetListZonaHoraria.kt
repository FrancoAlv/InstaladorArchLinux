package com.linux.createcompilador.hexagonal.zonaHoraria.application

import com.linux.createcompilador.hexagonal.base.UseCaseBaseOneElement
import com.linux.createcompilador.hexagonal.zonaHoraria.domain.data.ZonaHorariaSelect
import org.springframework.stereotype.Service
import java.time.ZoneId
import java.time.zone.ZoneRulesProvider
import kotlin.streams.toList
import zonedb.java.`tzdb$`
import java.time.LocalDateTime

@Service
class GetListZonaHoraria : UseCaseBaseOneElement<List<ZonaHorariaSelect>> {


    override suspend fun invoke(): List<ZonaHorariaSelect> {

        val allZoneIds = ZoneId.getAvailableZoneIds()
        return allZoneIds.stream().parallel().map {
            ZonaHorariaSelect(it)
        }.toList()

    }

}