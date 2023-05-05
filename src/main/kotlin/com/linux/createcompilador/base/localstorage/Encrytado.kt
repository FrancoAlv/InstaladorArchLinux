package com.restau.proyect.base.localstorage


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.nio.file.Path
import java.nio.file.Paths
import java.security.spec.KeySpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec
import kotlin.io.path.createDirectories
import kotlin.io.path.notExists
import kotlin.io.path.readText
import kotlin.io.path.writeText

class Encrytado<T>(
    private val typeToken: TypeToken<T>,
    private val storagePath: String
) {
    private val gson = Gson()

    fun save(data: T, fileName: String, password: String) {
        val path = getStoragePath(fileName)
        val jsonString = gson.toJson(data)
        val encryptedJson = encrypt(jsonString, password)
        path.writeText(encryptedJson)
    }

    fun load(fileName: String, password: String): T? {
        val path = getStoragePath(fileName)
        if (path.notExists()) {
            return null
        }
        val encryptedJson = path.readText()
        val decryptedJson = decrypt(encryptedJson, password)
        return gson.fromJson(decryptedJson, typeToken.type)
    }


    private fun getStoragePath(fileName: String): Path {
        val path = Paths.get(storagePath, fileName)
        val parent = path.parent
        if (parent.notExists()) {
            parent.createDirectories()
        }
        return path
    }

    private fun encrypt(jsonString: String, password: String): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val secretKey = SecretKeySpec(password.toByteArray(), "AES")
        val iv = IvParameterSpec(ByteArray(16)) // Utiliza un vector de inicialización (IV) predeterminado de 16 bytes
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv)
        val encrypted = cipher.doFinal(jsonString.toByteArray())
        return Base64.getEncoder().encodeToString(encrypted)
    }

    private fun decrypt(encryptedJson: String, password: String): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val secretKey = SecretKeySpec(password.toByteArray(), "AES")
        val iv =
            IvParameterSpec(ByteArray(16)) // Utiliza el mismo vector de inicialización (IV) predeterminado de 16 bytes
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv)
        val decoded = Base64.getDecoder().decode(encryptedJson)
        val decrypted = cipher.doFinal(decoded)
        return String(decrypted)
    }


    fun ByteArray.toHex(): String {
        return joinToString("") { "%02x".format(it) }
    }

    fun generateKey(password: String, keySize: Int = 256): String {
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val salt = "tu_salt".toByteArray() // Reemplaza "tu_salt" con un salt único para tu aplicación
        val iterations = 65536
        val keySpec: KeySpec = PBEKeySpec(password.toCharArray(), salt, iterations, keySize)
        val secretKey = factory.generateSecret(keySpec)
        val keyBytes = secretKey.encoded
        return keyBytes.toHex()
    }
}