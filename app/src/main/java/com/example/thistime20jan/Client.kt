package com.example.thistime20jan

import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.File
import java.io.FileInputStream
import java.net.Socket

class Client internal constructor(file : File) {
    init {
        // Create Client Socket connect to port 900
        try {
            Socket("localhost", 900).use { socket ->
                dataInputStream =
                    DataInputStream(
                        socket.getInputStream()
                    )
                dataOutputStream =
                    DataOutputStream(
                        socket.getOutputStream()
                    )
                println("Sending the File to the Server")
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