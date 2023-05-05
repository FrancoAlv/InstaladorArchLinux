package com.linux.createcompilador.views

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.linux.createcompilador.theme.gris


@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
@Preview
fun IdiomaComposable() {
    val stateVertical = rememberLazyListState(0)

    Column(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
        Image(
            painter = painterResource("dragables/logo.png"), "", contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth().height(150.dp)
        )
        Text(
            "ELIGA SU IDIOMA DE INSTALADOR", modifier = Modifier.fillMaxWidth().padding(10.dp),
            textAlign = TextAlign.Center
        )
        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
           Box(modifier = Modifier.align(Alignment.TopCenter).fillMaxWidth(0.6f).fillMaxHeight(0.8f)){
               LazyColumn(
                   modifier = Modifier.fillMaxWidth().fillMaxHeight().border(
                       BorderStroke(
                           2.dp,
                          gris
                       ), RoundedCornerShape(20.dp)
                   ).padding(10.dp).padding(end = 10.dp,),
                   state = stateVertical
               ) {
                   items(100) {
                       Row(
                           modifier = Modifier.fillMaxWidth().clickable {

                           },
                           horizontalArrangement = Arrangement.SpaceBetween
                       ) {
                           Text("Español $it", modifier = Modifier.align(Alignment.Bottom))
                           Icon(Icons.Outlined.Check, "")
                       }
                   }

               }
               VerticalScrollbar(
                   modifier = Modifier.align(Alignment.CenterEnd).padding(end = 5.dp, bottom = 5.dp, top = 5.dp).fillMaxHeight(),
                   adapter = rememberScrollbarAdapter(stateVertical)
               )
           }

        }


    }


}

