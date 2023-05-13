@file:Suppress("UNUSED_EXPRESSION")
package com.linux.createcompilador.components.AutoTexfiled


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.zIndex
import com.linux.createcompilador.theme.POPPINS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import kotlin.reflect.KProperty1


@Composable
inline fun <reified T> AutoCompleteTextField(
    params: ParamsData<T>,
    textStyle: TextStyle = MaterialTheme.typography.body1,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(),
) {
    var searchText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    var filteredItems by remember {
        mutableStateOf(emptyList<T>())
    }


    LaunchedEffect(params.items, searchText) {
        filtrarItems(params.items, searchText, params.atributo)
            .collect { items ->
                withContext(Dispatchers.IO) {
                    filteredItems = items
                }
            }
    }

    Column(modifier = Modifier.then(params.modifier), horizontalAlignment = Alignment.Start) {
        val textFieldWidth = remember { mutableStateOf(0.dp) }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(1f)
                .onFocusChanged {
                    expanded = it.isFocused
                }
                .onSizeChanged { coordinates ->
                    textFieldWidth.value = (coordinates.width * 0.8).dp
                },
            value = searchText,
            onValueChange = {
                searchText = it
                expanded = true
                params.onSearch(it)
            },
            label = { params.label?.let { Text(text = it) } },
            placeholder = { params.placeholder?.let { Text(text = it) } },
            trailingIcon = {
                params.trailingIcon()
            },
            textStyle = textStyle,
            singleLine = true,
            colors = colors,
        )
        if (expanded && filteredItems.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .width(with(LocalDensity.current){textFieldWidth.value})
                    .wrapContentHeight()
                    .zIndex(1f)
            ) {
                Card(
                    modifier = Modifier.width(with(LocalDensity.current){textFieldWidth.value}),
                    shape = MaterialTheme.shapes.medium,
                    elevation = 8.dp
                ) {
                    DropdownMenu(
                        expanded,
                        { expanded = false },
                        focusable = false,
                        modifier = Modifier
                            .width(with(LocalDensity.current){textFieldWidth.value})
                            .heightIn(max = 200.dp)
                    ) {
                        filteredItems.forEach { item ->
                            DropdownMenuItem(
                                {
                                    params.onItemSelected(item)
                                    expanded = false
                                    searchText = params.atributo.get(item).toString()
                                }, modifier = Modifier
                                    .wrapContentWidth()
                                    .background(MaterialTheme.colors.surface)
                            ) {
                                Text(
                                    text = params.atributo.get(item).toString(),
                                    fontFamily = FontFamily.POPPINS(),
                                    modifier = Modifier
                                        .wrapContentWidth()
                                        .padding(16.dp)
                                )
                            }
                            Divider(
                                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
                            )
                        }
                    }
                }
            }
        }
    }
}



inline fun <reified T> filtrarItems(items: List<T>, searchText: String, atributo: KProperty1<T,*>): Flow<List<T>> = flow {
    emit(items.asSequence().filter {
        return@filter atributo.get(it).toString().contains(searchText, ignoreCase = true)
    }.toList())
}.flowOn(Dispatchers.IO)