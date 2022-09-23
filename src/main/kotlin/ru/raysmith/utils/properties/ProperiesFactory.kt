package ru.raysmith.utils.properties

import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Path
import java.util.*

/** Contains factory methods for getting properties */
object PropertiesFactory {

    /** Return properties from resources
     * @param path file path from resources directory
     * @throws FileNotFoundException if file not found
     * */
    fun from(path: String) = Properties().apply {
        ClassLoader.getSystemClassLoader().getResourceAsStream(path)?.use { load(it) }
            ?: throw FileNotFoundException("Property file $path not found")
    }

    /** Return properties from resources or null if file not exist
     * @param path file path from resources directory
     * */
    fun fromOrNull(path: String) = ClassLoader.getSystemClassLoader().getResourceAsStream(path)?.let {
        Properties().apply { load(it) }
    }

    /** Return properties for [path] */
    fun from(path: Path): Properties = from(path.toFile())

    /** Return properties for [file] */
    fun from(file: File) = Properties().apply {
        file.inputStream().use { load(it) }
    }
}