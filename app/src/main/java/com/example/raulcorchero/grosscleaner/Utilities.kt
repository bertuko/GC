package com.example.raulcorchero.grosscleaner
import android.content.*
import java.io.*
import com.fasterxml.jackson.module.kotlin.*

class Utilities () {
    public fun GetConfiguration () {
//        val assetManager = getAssets()
//        val oXML = assetManager.open(R.string.configurationFileName)

    }

    public fun saveUserdata(ctx: Context, usuario: User) {
        val filename: String = R.string.userDataFileName.toString()
        val file: File = File(ctx.getFilesDir(), filename)
        var outputStream: FileOutputStream = ctx.openFileOutput(filename, Context.MODE_PRIVATE);
        val mapper = jacksonObjectMapper()
        mapper.writeValue(outputStream, usuario)
        outputStream.close();
    }

    public fun LoadUserdata(ctx: Context) : User {
        val filename: String = R.string.userDataFileName.toString()
        var fis: FileInputStream = ctx.openFileInput(filename)
        var isr: InputStreamReader = InputStreamReader(fis)
        val mapper = jacksonObjectMapper()
        var usuario: User = mapper.readValue<User>(isr.readText())
        return usuario
    }
}