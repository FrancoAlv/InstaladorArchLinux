package com.linux.createcompilador.IOD

import androidx.compose.runtime.*
import com.linux.createcompilador.ConfigManager
import com.linux.createcompilador.hexagonal.base.ViewModelBase
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

object Context {

    val value: ConfigurableApplicationContext =AnnotationConfigApplicationContext (ConfigManager::class.java)

    inline fun <reified T> viewmodel(): T {
        return value.getBean(T::class.java)
    }

    fun close() {
        value.close()
    }



}




@Composable
inline fun <reified T : ViewModelBase> viewmodels(): MutableState<T> {

    val state = remember { mutableStateOf(Context.viewmodel<T>()) }
    DisposableEffect(Unit) {
        onDispose {
            state.value.destroy()
        }
    }
    return state
}
