package com.dzk.hotfix

import org.junit.Test

import org.junit.Assert.*
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.lang.Exception

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
//        val clazz:Class<*> = Long.javaClass
//        print(clazz.isInstance(Int))
//        println(CharSequence::class.java.isAssignableFrom(String::class.java))
        val process:Process = Runtime.getRuntime().exec("adb --version")
        process.waitFor()
        var inputStream:InputStream? = null
        var baos:ByteArrayOutputStream? = null
        try {
            inputStream = process.inputStream
            var len:Int = -1
            val buffer = ByteArray(1024)
            baos = ByteArrayOutputStream()
            read@while (true){
                len = inputStream.read(buffer)
                when(len){
                    -1 -> {
                        break@read
                    }
                    else -> {
                        baos.write(buffer,0,len)
                    }
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }finally {
            baos?.flush()
            println(String(baos!!.toByteArray()))
            inputStream?.close()

            baos?.close()

        }

    }
}