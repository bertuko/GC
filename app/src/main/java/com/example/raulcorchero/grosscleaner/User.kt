package com.example.raulcorchero.grosscleaner

import java.math.BigDecimal

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

}