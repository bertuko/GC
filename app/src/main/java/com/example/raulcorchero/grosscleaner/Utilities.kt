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

    fun GetConfiguration () {
        var oXML = oContext.resources.openRawResource (R.xml.tables_configuration)

        var p = 0

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

    /*
    public fun saveSharedPreferences(usuario: User) {
        val sharedPref: SharedPreferences = oContext.getSharedPreferences(oContext.resources.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor  = sharedPref.edit()
        editor.putBoolean("PrimeraCarga", false)
        editor.putInt("GradoDiscapacidad", usuario.GradoDiscapacidad)
        editor.putInt("NumDescendientesMayores3", usuario.NumDescendientesMayores3)
        editor.putInt("NumDescendientesMenores3", usuario.NumDescendientesMenores3)
        editor.putInt("NumPagas", usuario.NumPagas)
        editor.putInt("PorcentajeRetencion", usuario.PorcentajeRetencion)
        editor.putBoolean("FuerzaRetencion", usuario.FuerzaRetencion)
        editor.putFloat("ImporteBruto", usuario.ImporteBruto)
        editor.putBoolean("ReduccionVivienda", usuario.ReduccionVivienda)
        editor.putInt("SituacionFamiliar", usuario.SituacionFamiliar)
        editor.putBoolean("FuerzaRetencion", usuario.FuerzaRetencion)
        editor.commit()
    }

    public fun loadSharedPreferences(): User {
        var usuario: User = User()
        val sharedPref: SharedPreferences = oContext.getSharedPreferences(oContext.resources.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor  = sharedPref.edit()
        usuario.PrimeraCarga = sharedPref.getBoolean("PrimeraCarga",true)
        usuario.GradoDiscapacidad = sharedPref.getInt("GradoDiscapacidad",0)
        usuario.NumDescendientesMayores3 = sharedPref.getInt("NumDescendientesMayores3",0)
        usuario.NumDescendientesMenores3 = sharedPref.getInt("NumDescendientesMenores3",0)
        usuario.NumPagas = sharedPref.getInt("NumPagas", 12)
        usuario.PorcentajeRetencion = sharedPref.getInt("PorcentajeRetencion",0)
        usuario.FuerzaRetencion = sharedPref.getBoolean("FuerzaRetencion", false)
        usuario.ImporteBruto = sharedPref.getFloat("ImporteBruto", 0f)
        usuario.ReduccionVivienda = sharedPref.getBoolean("ReduccionVivienda", false)
        usuario.SituacionFamiliar = sharedPref.getInt("SituacionFamiliar", 3)
        usuario.FuerzaRetencion = sharedPref.getBoolean("FuerzaRetencion", false)
        return usuario
    }
    */
}