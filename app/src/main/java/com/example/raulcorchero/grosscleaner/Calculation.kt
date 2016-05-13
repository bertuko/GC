package com.example.raulcorchero.grosscleaner

/**
 * Created by raul.palomares on 12/05/2016.
 */
class Calculation (){
/*
    val oConfig : Configuration = Configuration(true)
    val ConfigIRPF: Configuration_IRPF = Configuration_IRPF();
    val ConfigContribution:  Configuration_Contribution = Configuration_Contribution();
    val CalculateIRPF: Calculation_IRPF = Calculation_IRPF();
    val CalculateContribution: Calculation_Contribution = Calculation_Contribution();*/
    val CalculateDetail: Detail = Detail();

    fun Calculate(): Detail{
        val oConfig : Configuration = Configuration(true)
        var oContribution : Calculation_Contribution = Calculation_Contribution(oConfig)


        return CalculateDetail
    }
}

