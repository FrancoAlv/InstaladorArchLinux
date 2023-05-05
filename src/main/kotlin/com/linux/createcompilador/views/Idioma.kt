package com.linux.createcompilador.views

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.linux.createcompilador.components.CajaScroll


@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
@Preview
fun IdiomaComposable() {

    Column(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
        Image(
            painter = painterResource("dragables/logo.png"), "", contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth().height(150.dp)
        )
        Text(
            "ELIGA SU IDIOMA DE INSTALADOR", modifier = Modifier.fillMaxWidth().padding(10.dp),
            textAlign = TextAlign.Center
        )
        CajaScroll (heightbox = 1f){
            items(1) {
                Row(
                    modifier = Modifier.fillMaxWidth().clickable {

                    },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Español", modifier = Modifier.align(Alignment.Bottom))
                    Icon(Icons.Outlined.Check, "")
                }
            }
        }


    }

}

