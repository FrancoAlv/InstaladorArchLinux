package com.linux.createcompilador

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import com.linux.createcompilador.IOD.Context
import com.linux.createcompilador.router.Router
import com.linux.createcompilador.theme.DragAndDropTheme
import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.inputStream

@Composable
@Preview
fun App() {
    DragAndDropTheme{
        Router()
    }

}

fun main() {
    val version = System.getProperty("app.version") ?: "Development"
    application {
        Window(onCloseRequest = {
            Context.close()
            exitApplication()
        }, state = rememberWindowState(size = DpSize(900.dp,600.dp), position = WindowPosition.Aligned(
            Alignment.Center), isMinimized = false, placement = WindowPlacement.Floating), icon = appIcon, title = "Conveyor Compose for Desktop sample $version") {
            App()
        }
    }
}

private val appIcon: Painter? by lazy {
    // app.dir is set when packaged to point at our collected inputs.
    val appDirProp = System.getProperty("app.dir")
    val appDir = appDirProp?.let { Path.of(it) }
    // On Windows we should use the .ico file. On Linux, there's no native compound image format and Compose can't render SVG icons,
    // so we pick the 128x128 icon and let the frameworks/desktop environment rescale.
    var iconPath = appDir?.resolve("app.ico")?.takeIf { it.exists() }
    iconPath = iconPath ?: appDir?.resolve("icon-square-128.png")?.takeIf { it.exists() }
    if (iconPath?.exists() == true) {
        BitmapPainter(iconPath.inputStream().buffered().use { loadImageBitmap(it) })
    } else {
        null
    }
}
