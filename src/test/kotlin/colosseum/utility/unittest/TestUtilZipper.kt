package colosseum.utility.unittest

import colosseum.utility.UtilZipper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

internal class TestUtilZipper {
    @TempDir
    lateinit var tempInputDir: File
    @TempDir
    lateinit var tempOutputDir: File

    private lateinit var mockDirectTxt1: File
    private lateinit var mockDirectTxt2: File

    private lateinit var mockDescendantDir1: File
    private lateinit var mockDescendantDir2: File
    private lateinit var mockDescendantTxt1: File
    private lateinit var mockDescendantTxt2: File

    private lateinit var mockIndirectDir: File
    private lateinit var mockIndirectTxt: File

    @BeforeEach
    fun prepare() {
        mockDirectTxt1 = File(tempInputDir, "mockDirectTxt1.txt")
        mockDirectTxt1.writeText("mockDirectTxt1.txt")

        mockDirectTxt2 = File(tempInputDir, "mockDirectTxt2.txt")
        mockDirectTxt2.writeText("mockDirectTxt2.txt")

        mockDescendantDir1 = File(tempInputDir, "mockDescendantDir1")
        Assertions.assertTrue(mockDescendantDir1.mkdirs())
        mockDescendantDir2 = File(tempInputDir, "mockDescendantDir2")
        Assertions.assertTrue(mockDescendantDir2.mkdirs())

        mockDescendantTxt1 = File(mockDescendantDir1, "mockDescendantTxt1.txt")
        mockDescendantTxt1.writeText("mockDescendantTxt1.txt")

        mockDescendantTxt2 = File(mockDescendantDir2, "mockDescendantTxt2.txt")
        mockDescendantTxt2.writeText("mockDescendantTxt2.txt")

        mockIndirectDir = File(mockDescendantDir1, "mockIndirectDir")
        Assertions.assertTrue(mockIndirectDir.mkdirs())

        mockIndirectTxt = File(mockIndirectDir, "mockIndirectTxt.txt")
        mockIndirectTxt.writeText("mockIndirectTxt.txt")
    }

    @Test
    fun testZipper() {
        Assertions.assertDoesNotThrow {
            UtilZipper.zip(tempInputDir, tempOutputDir.resolve("output.zip"))
            UtilZipper.unzip(tempOutputDir.resolve("output.zip"), tempOutputDir)
        }

        Assertions.assertTrue(tempOutputDir.resolve("mockDirectTxt1.txt").exists())
        Assertions.assertTrue(tempOutputDir.resolve("mockDirectTxt1.txt").isFile)
        Assertions.assertFalse(tempOutputDir.resolve("mockDirectTxt1.txt").isDirectory)
        Assertions.assertTrue(tempOutputDir.resolve("mockDirectTxt1.txt").readText() == "mockDirectTxt1.txt")

        Assertions.assertTrue(tempOutputDir.resolve("mockDirectTxt2.txt").exists())
        Assertions.assertTrue(tempOutputDir.resolve("mockDirectTxt2.txt").isFile)
        Assertions.assertFalse(tempOutputDir.resolve("mockDirectTxt2.txt").isDirectory)
        Assertions.assertTrue(tempOutputDir.resolve("mockDirectTxt2.txt").readText() == "mockDirectTxt2.txt")

        Assertions.assertTrue(tempOutputDir.resolve("mockDescendantDir1").exists())
        Assertions.assertFalse(tempOutputDir.resolve("mockDescendantDir1").isFile)
        Assertions.assertTrue(tempOutputDir.resolve("mockDescendantDir1").isDirectory)
        Assertions.assertTrue(tempOutputDir.resolve("mockDescendantDir1").resolve("mockDescendantTxt1.txt").exists())
        Assertions.assertTrue(tempOutputDir.resolve("mockDescendantDir1").resolve("mockDescendantTxt1.txt").isFile)
        Assertions.assertFalse(tempOutputDir.resolve("mockDescendantDir1").resolve("mockDescendantTxt1.txt").isDirectory)
        Assertions.assertTrue(tempOutputDir.resolve("mockDescendantDir1").resolve("mockDescendantTxt1.txt").readText() == "mockDescendantTxt1.txt")
        Assertions.assertTrue(tempOutputDir.resolve("mockDescendantDir1").resolve("mockIndirectDir").exists())
        Assertions.assertFalse(tempOutputDir.resolve("mockDescendantDir1").resolve("mockIndirectDir").isFile)
        Assertions.assertTrue(tempOutputDir.resolve("mockDescendantDir1").resolve("mockIndirectDir").isDirectory)
        Assertions.assertTrue(tempOutputDir.resolve("mockDescendantDir1").resolve("mockIndirectDir").resolve("mockIndirectTxt.txt").exists())
        Assertions.assertTrue(tempOutputDir.resolve("mockDescendantDir1").resolve("mockIndirectDir").resolve("mockIndirectTxt.txt").isFile)
        Assertions.assertFalse(tempOutputDir.resolve("mockDescendantDir1").resolve("mockIndirectDir").resolve("mockIndirectTxt.txt").isDirectory)
        Assertions.assertTrue(tempOutputDir.resolve("mockDescendantDir1").resolve("mockIndirectDir").resolve("mockIndirectTxt.txt").readText() == "mockIndirectTxt.txt")

        Assertions.assertTrue(tempOutputDir.resolve("mockDescendantDir2").exists())
        Assertions.assertFalse(tempOutputDir.resolve("mockDescendantDir2").isFile)
        Assertions.assertTrue(tempOutputDir.resolve("mockDescendantDir2").isDirectory)
        Assertions.assertTrue(tempOutputDir.resolve("mockDescendantDir2").resolve("mockDescendantTxt2.txt").exists())
        Assertions.assertTrue(tempOutputDir.resolve("mockDescendantDir2").resolve("mockDescendantTxt2.txt").isFile)
        Assertions.assertFalse(tempOutputDir.resolve("mockDescendantDir2").resolve("mockDescendantTxt2.txt").isDirectory)
        Assertions.assertTrue(tempOutputDir.resolve("mockDescendantDir2").resolve("mockDescendantTxt2.txt").readText() == "mockDescendantTxt2.txt")
    }
}
