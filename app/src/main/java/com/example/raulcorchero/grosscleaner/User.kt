package com.example.raulcorchero.grosscleaner

data class MyStateObject(val name: String, val age: Int)

class User {

    var SituacionFamiliar: TipoSituacionFamiliar = TipoSituacionFamiliar.Sit3
    var GradoDiscapacidad: Int = 0
    var NumDescendientesMenores3: Int = 0
    var NumDescendientesMayores3: Int = 0
    var FuerzaRetencion: Boolean = false
    var PorcentajeRetencion: Int = 0
    var ReduccionVivienda: Boolean = false

    // Datos paga
    var ImporteBruto: Float = 0f
    var NumPagas: Int = 12

    constructor() {
        if (!esNuevoUser()) {
            LoadUser()
        }
    }

    private fun esNuevoUser(): Boolean {
        return true
    }

    private fun LoadUser(): User {
        var Usuario: User = User()
        var u: Utilities
        //Usuario = u.ReadXMLFile("asdads")
        return Usuario
    }

    fun grabarUser(): Boolean {


        return true
    }

}