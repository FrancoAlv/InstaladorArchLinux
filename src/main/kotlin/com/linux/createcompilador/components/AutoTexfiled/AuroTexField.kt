@file:Suppress("UNUSED_EXPRESSION")
package com.linux.createcompilador.components.AutoTexfiled


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.linux.createcompilador.theme.POPPINS


@Composable
inline fun <reified T> AutoCompleteTextField(
    params: ParamsData<T>,
    textStyle: TextStyle = MaterialTheme.typography.body1,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(),
) {
    var searchText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val filteredItems = remember(params.items, searchText) {
        params.items.asSequence().filter {
            return@filter params.atributo.get(it).toString().contains(searchText, ignoreCase = true)
        }.toList()
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
                    .width(textFieldWidth.value)
                    .wrapContentHeight()
                    .zIndex(1f)
            ) {
                Card(
                    modifier = Modifier.width(textFieldWidth.value),
                    shape = MaterialTheme.shapes.medium,
                    elevation = 8.dp
                ) {
                    DropdownMenu(
                        expanded,
                        { expanded = false },
                        focusable = false,
                        modifier = Modifier
                            .width(textFieldWidth.value)
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

