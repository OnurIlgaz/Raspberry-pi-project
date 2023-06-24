package com.example.thistime20jan

import android.util.Log
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.File
import java.io.FileInputStream
import java.net.Socket

class Client constructor(file : File) : Runnable {

    val file : File

    init {
        this.file = file
    }

    override fun run(){
        // Create Client Socket connect to port 900
        try {
            print("trying")
            Socket("10.134.116.66", 9987).use { socket ->
                dataInputStream =
                    DataInputStream(
                        socket.getInputStream()
                    )
                dataOutputStream =
                    DataOutputStream(
                        socket.getOutputStream()
                    )
                // Call SendFile Method
                sendFile(file)
                dataInputStream!!.close()
                dataInputStream!!.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private var dataOutputStream: DataOutputStream? = null
        private var dataInputStream: DataInputStream? = null

        // sendFile function define here
        @Throws(Exception::class)
        private fun sendFile(file: File) {
            var bytes = 0
            // Open the File where he located in your pc
            val fileInputStream = FileInputStream(file)

            // Here we send the File to Server
            dataOutputStream!!.writeLong(file.length())
            // Here we break file into chunks
            val buffer = ByteArray(4 * 1024)
            while (fileInputStream.read(buffer).also { bytes = it }
                != -1) {
                // Send the file to Server Socket
                dataOutputStream!!.write(buffer, 0, bytes)
                dataOutputStream!!.flush()
            }
            // close the file here
            fileInputStream.close()
        }
    }

}