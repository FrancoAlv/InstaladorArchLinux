package com.linux.createcompilador.hexagonal.zonaHoraria.infrastructure.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.linux.createcompilador.hexagonal.zonaHoraria.domain.data.LatLng
import com.linux.createcompilador.hexagonal.zonaHoraria.domain.mappers.latLngToImageCoordinates


@Composable
fun MapWithMarkers(
    mapImageResource: String,
    markers: List<LatLng>,
    modifier: Modifier,
    topLeft: LatLng,
    bottomRight: LatLng
) {

    val height=350
    val with=550


    val imageSize = Pair((with.dp).value, (height.dp).value)
    val imageMarkers = markers.map { latLngToImageCoordinates(it, topLeft, bottomRight, imageSize) }
    Box (modifier=modifier){
        Image(
            painter = painterResource(mapImageResource),
            contentDescription = "Map image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.width(with.dp).height(height.dp)
        )
        imageMarkers.forEach { marker ->
            Icon(
                imageVector = Icons.Default.Place,
                contentDescription = "Marker",
                modifier = Modifier
                    .size(24.dp)
                    .offset(x = marker.x.dp, y = marker.y.dp),
                tint = Color.Red
            )
        }
    }
}


