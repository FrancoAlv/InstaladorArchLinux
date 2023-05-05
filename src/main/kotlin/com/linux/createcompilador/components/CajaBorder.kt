package com.linux.createcompilador.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun CajaBorder(with: Float = 0.6f, height: Float = 0.8f,heightbox: Float = 1f, content: @Composable BoxScope.() -> Unit) {
    Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(heightbox)) {
        Box(modifier = Modifier.align(Alignment.TopCenter).fillMaxWidth(with).fillMaxHeight(height)) {
            content()
        }
    }
}