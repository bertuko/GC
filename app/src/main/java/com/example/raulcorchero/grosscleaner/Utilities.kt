package com.example.raulcorchero.grosscleaner
import android.content.*
import java.io.*
import com.fasterxml.jackson.module.kotlin.*
import com.fasterxml.jackson.module.kotlin.readValue

class Utilities {
    private var oContext: Context

    constructor (c: Context) {
        this.oContext = c
    }

    public fun saveUserdata(usuario: User) {
        val filename: String = oContext.resources.getString(R.string.userDataFileName)
        val file: File = File(this.oContext.getFilesDir(), filename)
        var outputStream: FileOutputStream = this.oContext.openFileOutput(filename, Context.MODE_PRIVATE);
        val mapper = jacksonObjectMapper()
        mapper.writeValue(outputStream, usuario)
        outputStream.close();
    }

    public fun LoadUserdata() : User {
        val filename: String = oContext.resources.getString(R.string.userDataFileName)
        var fis: FileInputStream = this.oContext.openFileInput(filename)
        var isr: InputStreamReader = InputStreamReader(fis)
        val mapper = jacksonObjectMapper()
        var usuario: User = mapper.readValue<User>(isr.readText())
        return usuario
    }

    public fun ExistsUserdata() : Boolean {
        val filename: String = oContext.resources.getString(R.string.userDataFileName)
        val file: File = File(this.oContext.getFilesDir(), filename);
        return file.exists();
    }
}