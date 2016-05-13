package com.example.raulcorchero.grosscleaner

import kotlin.reflect.jvm.internal.impl.descriptors.impl.CompositePackageFragmentProvider

/**
 * Created by raul.palomares on 12/05/2016.
 * Broken by raul.corchero on 13/05/2016.
 */
class Calculation_Contribution (oConfig:  Configuration){
    var Perceptor: User = User();
    var ConfigCC: Configuration_Contribution_CommonContingencies = oConfig.Contribution.CommonContigencies
    var ConfigPC: Configuration_Contribution_ProfessionalContingencies = oConfig.Contribution.ProfessionalContingencies
    var ConfigCCS: Configuration_Contribution_CommonContingencies_Scale = ConfigCC.Scale[0]
    var BASE: Float = 0f
    var CuotaCC: Float = 0f
    var CuotaFP: Float = 0f
    var CuotaDes: Float = 0f
    var MULT: Int = 0
    var TOPMIN: Float = 0f
    var TOPMAX: Float = 0f



    fun Calc (Amount:Float, Anual:Boolean):Float{
        //Si me llega un amount Anual el multiplicador sera 1
        //si me llaga un amount Mensual el multiplicador sera 12
        if (Anual) MULT = 1 else MULT = 12
        TOPMIN = getTopMin()
        TOPMAX = getTopMax()
        BASE = Amount
        if (Amount > TOPMAX) BASE = TOPMAX
        if (Amount < TOPMIN) BASE = TOPMIN
        CuotaCC = BASE * ConfigCC.Percentage
        CuotaFP = BASE * ConfigPC.PercentageFP
        CuotaDes = BASE * ConfigPC.PercentageDesemployee

        return CuotaCC + CuotaFP + CuotaDes
    }

    /**
     * Miramos si es Jornada parcial para modificarle el Minimo
     */
    fun getTopMin(): Float{
        var min:Float = 0f
        if(Perceptor.Horas < 40) {
            min = (ConfigCCS.Min * ((Perceptor.Horas * 100)/40)) /100
        }else{
            min = ConfigCCS.Min
        }
        return min * MULT
    }

    /**
     * Miramos si es Jornada parcial para modificarle el Maximo
     */
    fun getTopMax(): Float{
        var max:Float = 0f
        if(Perceptor.Horas < 40) {
            max = (ConfigCCS.Max * ((Perceptor.Horas * 100)/40)) /100
        }else{
            max = ConfigCCS.Max
        }
        return max * MULT
    }
}



