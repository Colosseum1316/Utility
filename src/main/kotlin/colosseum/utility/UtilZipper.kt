package colosseum.utility

import java.io.File
import java.io.IOException
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
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
        val targetPath = outputDirectory.toPath()
        ZipInputStream(Files.newInputStream(zipFile.toPath())).use { zis ->
            var entry = zis.nextEntry
            while (entry != null) {
                val newPath = targetPath.resolve(entry.name).normalize()

                if (!newPath.startsWith(targetPath)) {
                    throw IOException("Bad zip entry: ${entry.name}")
                }

                if (entry.isDirectory) {
                    Files.createDirectories(newPath)
                } else {
                    Files.createDirectories(newPath.parent)
                    Files.copy(zis, newPath, StandardCopyOption.REPLACE_EXISTING)
                }

                zis.closeEntry()
                entry = zis.nextEntry
            }
        }
    }
}
