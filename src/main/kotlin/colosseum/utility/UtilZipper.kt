package colosseum.utility

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.logging.*
import java.util.zip.*

object UtilZipper {
    @JvmStatic
    fun getFileList(fileList: MutableList<File>, node: File) {
        // add file only
        if (node.isFile) {
            fileList.add(node)
        } else if (node.isDirectory) {
            val subNote = node.list()
            if (subNote != null) {
                for (filename in subNote) {
                    getFileList(fileList, File(node, filename))
                }
            }
        }
    }

    /**
     * @param sourceFolder The folder that has everything to be zipped.
     * @param outputZipFile Zip file output.
     * @param subFolders Subdirectories relative to [sourceFolder]. Direct descendant.
     * @param individualFiles Individual files that are right in [sourceFolder]
     *
     * @throws IOException If any IO error occurs during zipping.
     */
    @JvmStatic
    @Throws(IOException::class)
    fun zip(sourceFolder: File, outputZipFile: File, subFolders: List<File>, individualFiles: List<File>) {
        val fileList: MutableList<File> = ArrayList()
        val buffer = ByteArray(2048)

        FileOutputStream(outputZipFile).use { fileOutputStream ->
            BufferedOutputStream(fileOutputStream).use { bufferedOutputStream ->
                ZipOutputStream(bufferedOutputStream).use { zipOutputStream ->
                    var entry: ZipEntry

                    for (file in individualFiles) {
                        fileList.add(file)
                    }

                    for (folder in subFolders) {
                        getFileList(fileList, folder)
                    }

                    for (f in fileList) {
                        entry = ZipEntry(f.name)
                        zipOutputStream.putNextEntry(entry)

                        FileInputStream(sourceFolder.resolve(f)).use { fileInputStream ->
                            var len: Int
                            while ((fileInputStream.read(buffer).also { len = it }) > 0) {
                                zipOutputStream.write(buffer, 0, len)
                            }
                        }
                    }
                }
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
