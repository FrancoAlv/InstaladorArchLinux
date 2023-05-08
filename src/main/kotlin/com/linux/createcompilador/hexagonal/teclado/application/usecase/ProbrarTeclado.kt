package com.linux.createcompilador.hexagonal.teclado.application.usecase

import com.linux.createcompilador.hexagonal.teclado.domain.preferent.TypeTecladoPreferent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader

@Service
class ProbrarTeclado(
    @Autowired private val typeTecladoPreferent: TypeTecladoPreferent,
) {
    operator fun invoke() {
       try{
           typeTecladoPreferent.getteclado()?.let {
               val osName = System.getProperty("os.name")

               if (osName.lowercase().startsWith("linux")) {
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
       }catch (e:Exception){
           println(e)
       }
    }

}