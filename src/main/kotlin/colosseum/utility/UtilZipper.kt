package colosseum.utility

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.util.logging.*
import java.util.zip.*

object UtilZipper {

    /**
     * @param sourceDir The folder that has everything to be zipped.
     * @param outputZipFile Zip file output.
     *
     * @throws IOException If any IO error occurs during zipping.
     */
    @JvmStatic
    @Throws(IOException::class)
    fun zip(sourceDir: File, outputZipFile: File) {
        val sourcePath = sourceDir.toPath()
        val outputPath = outputZipFile.toPath()
        ZipOutputStream(Files.newOutputStream(outputPath)).use { zos ->
            Files.walk(sourcePath).forEach { path ->
                if (Files.isDirectory(path)) {
                    return@forEach
                }
                val relativePath: Path = sourcePath.relativize(path)
                val zipEntry = ZipEntry(relativePath.toString().replace(FileSystems.getDefault().separator, "/"))
                zos.putNextEntry(zipEntry)
                Files.copy(path, zos)
                zos.closeEntry()
            }
        }
    }

    /**
     * @param zipFile Zip file input.
     * @param outputDirectory The directory to put all unzipped in.
     *
     * @throws IOException If any I/O error occurs during unzipping.
     */
    @JvmStatic
    @Throws(IOException::class)
    fun unzip(zipFile: File, outputDirectory: File) {
        FileInputStream(zipFile).use { fileInputStream ->
            BufferedInputStream(fileInputStream).use { bufferedInputStream ->
                ZipInputStream(bufferedInputStream).use { zipInputStream ->
                    var entry: ZipEntry?

                    while ((zipInputStream.nextEntry.also { entry = it }) != null) {
                        var size: Int
                        val buffer = ByteArray(2048)

                        val file = outputDirectory.resolve(entry!!.name)

                        if (file.isDirectory && !file.exists()) {
                            file.mkdirs()
                            continue
                        }

                        FileOutputStream(file).use { fileOutputStream ->
                            BufferedOutputStream(fileOutputStream, buffer.size).use { bufferedOutputStream ->
                                while ((zipInputStream.read(buffer, 0, buffer.size).also { size = it }) != -1) {
                                    bufferedOutputStream.write(buffer, 0, size)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
