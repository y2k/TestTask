package com.example.sdk.utils

import android.content.Context
import java.io.FileOutputStream
import java.io.IOException

fun copyDatabase(context: Context, databaseName: String) {
    val dbPath = context.getDatabasePath(databaseName)

    if (dbPath.exists()) {
        return
    }

    dbPath.parentFile.mkdirs()

    try {
        val inputStream = context.assets.open(databaseName)
        val output = FileOutputStream(dbPath)

        val buffer = ByteArray(8192)
        var length = inputStream.read(buffer, 0, 8192)

        while (length > 0) {
            output.write(buffer, 0, length)
            length = inputStream.read(buffer, 0, 8192)
        }

        output.flush()
        output.close()
        inputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}
