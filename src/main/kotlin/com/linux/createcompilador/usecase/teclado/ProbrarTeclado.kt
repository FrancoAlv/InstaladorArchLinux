package com.linux.createcompilador.usecase.teclado

import com.linux.createcompilador.dominan.preferent.TypeTecladoPreferent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader

@Service
class ProbrarTeclado(
    @Autowired private val typeTecladoPreferent: TypeTecladoPreferent,
) {
    operator fun invoke() {
        typeTecladoPreferent.getteclado()?.let {
            val osName = System.getProperty("os.name")

            if (osName.toLowerCase().startsWith("linux")) {
                val command = it.comando ?: "echo 'Comando fallando'"

                // Crear ProcessBuilder con el comando
                val processBuilder = ProcessBuilder(*command.split(" ").toTypedArray())

                // Ejecutar el comando y capturar la salida
                val process = processBuilder.start()
                val reader = BufferedReader(InputStreamReader(process.inputStream))

                // Leer la salida del comando
                val output = reader.readText()

                // Esperar a que finalice el proceso y obtener el código de salida
                val exitCode = process.waitFor()

                println("Salida del comando: $output")
                println("Código de salida: $exitCode")
            } else {
                println("El sistema operativo no es Linux. No se ejecutará el comando.")
            }

        }
    }

}