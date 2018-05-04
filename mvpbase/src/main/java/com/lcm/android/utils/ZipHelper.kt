package com.lcm.android.utils

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.util.zip.*

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/5/3 16:34
 * Desc:
 * *****************************************************************
 */
object ZipHelper {

    /**
     * zlib decompress 2 String
     */
    @JvmStatic
    fun decompressToStringForZlib(bytesToDecompress: ByteArray): String? {
        var bytesDecompressed = decompressForZlib(bytesToDecompress)
        var returnValue: String? = null
        try {
            if (bytesDecompressed == null) return returnValue
            returnValue = String(bytesDecompressed, 0, bytesDecompressed.size, Charset.forName("UTF-8"))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        return returnValue
    }

    /**
     * zlib decompress 2 byte
     */
    @JvmStatic
    fun decompressForZlib(bytesToDecompress: ByteArray): ByteArray? {
        var returnValue: ByteArray? = null

        var inflater: Inflater = Inflater()
        var numberOfBytesToDecompress: Int = bytesToDecompress.size;
        inflater.setInput(bytesToDecompress, 0, numberOfBytesToDecompress)

        var bufferSizeInBytes = numberOfBytesToDecompress
        var numberOfBytesDecompressedSoFar = 0
        var bytesDecompressedSoFar: MutableList<Byte> = mutableListOf()

        try {
            while (!inflater.needsInput()) {
                var bytesDecompressedBuffer: ByteArray = ByteArray(bufferSizeInBytes)
                var numberOfBytesDecompressedThisTime: Int = inflater.inflate(bytesDecompressedBuffer)
                numberOfBytesDecompressedSoFar += numberOfBytesDecompressedThisTime
                for (index in 0..numberOfBytesDecompressedThisTime) {
                    bytesDecompressedSoFar.add(bytesDecompressedBuffer[index])
                }
            }
            returnValue = ByteArray(bytesDecompressedSoFar.size)
            for (index in 0..bytesDecompressedSoFar.size) {
                returnValue[index] = bytesDecompressedSoFar[index]
            }

        } catch (e: DataFormatException) {
            e.printStackTrace()
        }
        inflater.end()
        return returnValue
    }


    /**
     * zlib compress 2 byte
     */
    @JvmStatic
    fun compressForZlib(bytesToCompress: ByteArray): ByteArray {
        var deflater: Deflater = Deflater()
        deflater.setInput(bytesToCompress)
        deflater.finish()

        var bytesCompressed: ByteArray = ByteArray(Short.MAX_VALUE.toInt())
        var numberOfBytesAfterCompression: Int = deflater.deflate(bytesCompressed)
        var returnValues: ByteArray = ByteArray(numberOfBytesAfterCompression)

        System.arraycopy(bytesCompressed, 0, returnValues, 0, numberOfBytesAfterCompression)
        return returnValues
    }

    /**
     * zlib compress 2 byte
     */
    @JvmStatic
    fun compressForZlib(stringToCompress: String): ByteArray? {
        var returnValues: ByteArray? = null
        try {
            returnValues = compressForZlib(stringToCompress.toByteArray(Charset.forName("UTF-8")))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return returnValues
    }

    @JvmStatic
    fun compressForGzip(string: String): ByteArray? {
        var os: ByteArrayOutputStream? = null
        var gos: GZIPOutputStream? = null

        try {
            os = ByteArrayOutputStream(string.length)
            gos = GZIPOutputStream(os)
            if(gos==null) return null
            gos.write(string.toByteArray(Charset.forName("UTF-8")))
            return os.toByteArray()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            okhttp3.internal.Util.closeQuietly(gos)
            okhttp3.internal.Util.closeQuietly(os)
        }
        return null
    }


    /**
     * gzip decompress 2 string
     */
    @JvmStatic
    fun decompressForGzip(compressed:ByteArray):String?{
        val BUFFER_SIZE = compressed.size
        var gis:GZIPInputStream? = null
        var bis:ByteArrayInputStream? =null
        try {
            bis = ByteArrayInputStream(compressed)
            gis = GZIPInputStream(bis,BUFFER_SIZE)
            var string:StringBuilder = StringBuilder()
            var data:ByteArray = ByteArray(BUFFER_SIZE)
            var bytesRead:Int
            while (true){
                bytesRead = gis.read(data)
                if (bytesRead == -1) {
                    break
                }else{
                    string.append(String(data,0,bytesRead, Charset.forName("UTF-8")))
                }
            }
            return string.toString()
        }catch (e:IOException){
            e.printStackTrace()
        }finally {
            okhttp3.internal.Util.closeQuietly(gis)
            okhttp3.internal.Util.closeQuietly(bis)
        }
        return null
    }


}