package com.example.raulcorchero.grosscleaner

class User () {
    var PrimeraCarga: Boolean = true
    var SituacionFamiliar: Int = 3
    var GradoDiscapacidad: Int = 0
    var NumDescendientesMenores3: Int = 0
    var NumDescendientesMayores3: Int = 0
    var FuerzaRetencion: Boolean = false
    var PorcentajeRetencion: Float = 0f
    var ReduccionVivienda: Boolean = false
    var EscalaDiscapacidad: Int = 1
    var MovilPerceptor: Boolean = false
    // Datos paga
    var ImporteBruto: Float = 0f
    var NumPagas: Int = 12
    var Horas: Int = 40
}

//data class DataUser (
//        var SituacionFamiliar: TipoSituacionFamiliar,
//        var GradoDiscapacidad: Int,
//        var NumDescendientesMenores3: Int,
//        var NumDescendientesMayores3: Int,
//        var FuerzaRetencion: Boolean,
//        var PorcentajeRetencion: Int,
//        var ReduccionVivienda: Boolean,
//        // Datos paga
//        var ImporteBruto: Float,
//        var NumPagas: Int
//)