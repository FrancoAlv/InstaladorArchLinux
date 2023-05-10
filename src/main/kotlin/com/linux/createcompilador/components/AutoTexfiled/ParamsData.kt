package com.linux.createcompilador.components.AutoTexfiled

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlin.reflect.KProperty1

data class ParamsData<T>(
    val items: List<T>,
    val onItemSelected: (T) -> Unit,
    val modifier: Modifier = Modifier,
    val label: String? = null,
    val placeholder: String? = null,
    val atributo: KProperty1<T, *>,
    val trailingIcon: @Composable (() -> Unit) = {},
    val onSearch: (search: String) -> Unit = {},
)
