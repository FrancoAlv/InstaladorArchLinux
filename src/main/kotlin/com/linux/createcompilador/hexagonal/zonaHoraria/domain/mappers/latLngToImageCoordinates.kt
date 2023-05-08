package com.linux.createcompilador.hexagonal.zonaHoraria.domain.mappers

import com.linux.createcompilador.hexagonal.zonaHoraria.domain.data.LatLng
import com.linux.createcompilador.hexagonal.zonaHoraria.domain.data.Marker
import kotlin.math.PI
import kotlin.math.ln
import kotlin.math.tan


fun latLngToMercator(latLng: LatLng): Pair<Double, Double> {
    val earthRadius = 6378137.0 // radio de la Tierra en metros

    val x = earthRadius * latLng.longitude * PI / 180
    val y = earthRadius * ln(tan(PI / 4 + latLng.latitude * PI / 360))

    return Pair(x, y)
}


fun mercatorToImageCoordinates(
    mercator: Pair<Double, Double>,
    topLeft: Pair<Double, Double>,
    bottomRight: Pair<Double, Double>,
    imageSize: Pair<Float, Float>
): Marker {
    val xRange = bottomRight.first - topLeft.first
    val yRange = topLeft.second - bottomRight.second
    val xRatio = (mercator.first - topLeft.first) / xRange
    val yRatio = (topLeft.second - mercator.second) / yRange
    val x = (imageSize.first * xRatio).toFloat()
    val y = (imageSize.second * yRatio).toFloat()
    return Marker(x, y)
}



fun latLngToImageCoordinates(
    latLng: LatLng,
    topLeft: LatLng,
    bottomRight: LatLng,
    imageSize: Pair<Float, Float>
): Marker {
    val topLeftMercator = latLngToMercator(topLeft)
    val bottomRightMercator = latLngToMercator(bottomRight)
    val mercator = latLngToMercator(latLng)

    return mercatorToImageCoordinates(mercator, topLeftMercator, bottomRightMercator, imageSize)
}
