package com.restau.proyect.base.localstorage


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

@Suppress("IMPLICIT_NOTHING_TYPE_ARGUMENT_AGAINST_NOT_NOTHING_EXPECTED_TYPE")
 class StorageService<T>(
    private val entityType: TypeToken<T>,
    val namedocument: String,
    val key: String
) {
    protected val storageDirectory = "storage"

    private val gson: Gson = Gson()

    val storageService = Encrytado(
        typeToken = entityType,
        storagePath = "${this.storageDirectory}\\$namedocument"
    )

    //Como voy a crear archivos que no se van a encryptar y no veo necesario lo voy poner en false
    // en caso sea neceserop debe validar un caso de dev o pro como estaba antes
    val version = false

    protected fun getStoragePath(): String {
        val typeName = namedocument
        val sanitizedEntityName = typeName.replace(Regex("[^a-zA-Z0-9]"), "_")
        val storagePath = Paths.get(storageDirectory, sanitizedEntityName).toString()
        return storagePath
    }

    open fun save(obj: T, filename: String) {
        val storagePath = getStoragePath()
        val filePath = Paths.get(storagePath, "$filename.json")

        try {
            Files.createDirectories(Paths.get(storagePath))

            File(filePath.toString()).writeText(gson.toJson(obj))
            if (version) {
                storageService.save(obj, "$filename.json", storageService.generateKey(key, 64))
            }

        } catch (e: IOException) {
            throw RuntimeException("Error al guardar el objeto en el archivo", e)
        }
    }

    open fun load(filename: String): T? {
        val storagePath = getStoragePath()
        val filePath = Paths.get(storagePath, "$filename.json")

        return try {
            if (version) {
                storageService.load("$filename.json", storageService.generateKey(key, 64))
            } else if (Files.exists(filePath)) {
                gson.fromJson(File(filePath.toString()).readText(), entityType.type)
            } else {
                null
            }
        } catch (e: IOException) {
            throw RuntimeException("Error al leer el objeto desde el archivo", e)
        }
    }

    open fun getAll(): List<T> {
        val storagePath = getStoragePath()
        val directory = File(storagePath)
        val files = directory.listFiles { _, name -> name.endsWith(".json") } ?: return emptyList()

        return files.mapNotNull { file ->
            try {
                gson.fromJson(file.readText(), entityType.type)
            } catch (e: IOException) {
                null
            }
        }
    }

    open fun delete(filename: String) {
        val storagePath = getStoragePath()
        val filePath = Paths.get(storagePath, "$filename.json")

        try {
            Files.deleteIfExists(filePath)
        } catch (e: IOException) {
            throw RuntimeException("Error al eliminar el archivo", e)
        }
    }
}