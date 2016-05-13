package com.example.raulcorchero.grosscleaner

/**
 * Created by raul.palomares on 12/05/2016.
 */
class Calculation (){
    var oUser: User = User()

    constructor (_oUser: User) : this() {
        this.oUser = _oUser
    }

    fun Calculate(): Detail{
        val oConfig : Configuration = Configuration(true)
        var oContribution : Calculation_Contribution = Calculation_Contribution(oConfig, this.oUser)
        var oIRPF : Calculation_IRPF = Calculation_IRPF(oConfig, this.oUser)
        var oDetail: Detail = Detail()
        var fBrutoPorPaga: Float = this.oUser.ImporteBruto / this.oUser.NumPagas
        var TipoIRPF : Float = 0f
        if (oUser.FuerzaRetencion == true)
            TipoIRPF = oUser.PorcentajeRetencion
        else
            TipoIRPF = oIRPF.TIPO

        oDetail.CuotaCotizaciones = oContribution.Calc(false)
        oDetail.PagaMensual = fBrutoPorPaga - (oDetail.CuotaCotizaciones + (fBrutoPorPaga * TipoIRPF ) )
        oDetail.PagaExtra = fBrutoPorPaga - ( fBrutoPorPaga * TipoIRPF )

        return oDetail
    }
}

