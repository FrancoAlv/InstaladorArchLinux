package com.linux.createcompilador.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.linux.createcompilador.theme.gris


@Composable
fun CajaScroll(with: Float = 0.6f, height: Float = 0.8f,heightbox: Float=1f, heightlist: Float = 1f,  content: LazyListScope.() -> Unit){
    val stateVertical = rememberLazyListState(0)
    CajaBorder(with,height,heightbox) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(heightlist).border(
                BorderStroke(
                    2.dp,
                    gris
                ), RoundedCornerShape(20.dp)
            ).padding(10.dp).padding(end = 10.dp),
            state = stateVertical
        ) {
            content()
        }
        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).padding(end = 5.dp, bottom = 5.dp, top = 5.dp)
                .fillMaxHeight(),
            adapter = rememberScrollbarAdapter(stateVertical)
        )
    }
}