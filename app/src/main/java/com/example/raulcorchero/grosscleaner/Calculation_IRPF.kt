package com.example.raulcorchero.grosscleaner

import java.text.DecimalFormat

class Calculation_IRPF ( oConfig: Configuration, oUser: User ) {
    var ConfigIRPF: Configuration_IRPF = oConfig.IRPF
    var IRPFDesc: Configuration_IRPF_Descendants = ConfigIRPF.Descendants;
    var IRPFQLimits: Configuration_IRPF_QuantitativeLimits = ConfigIRPF.QuantitativeLimits;
    var CalcContribution : Calculation_Contribution = Calculation_Contribution(oConfig, oUser)
    var Perceptor: User = oUser;
    var MINOPAGO: Float = 0.00f;
    var MINPERFA: Float = 0.00f;
    var CUOTA: Float = 0.00f;
    var CUOTA1: Float = 0.00f;
    var CUOTA2: Float = 0.00f;
    var TIPO: Float = 0.00f;
    var BASE: Float = 0.00f;
    var REDU: Float = 0.00f;
    var PENSION: Float = 0.00f;
    var HIJOS: Float = 0.00f;
    var DESEM: Float = 0.00f;
    var CONYUGE: Float = 0.00f;
    var RNTREDU: Float = 0.00f;
    var GASTOSGEN: Float = 0.00f;
    var INCREGASDISTRA: Float = 0.00f;
    var INCREGASMOVIL: Float = 0.00f;
    var OTRASGASTOS: Float = 0.00f;
    var GASTOS: Float = 0.00f;
    var RNT: Float = 0.00f;
    var RETRIB: Float = 0.00f;
    var IRREGULAR1: Float = 0.00f;
    var IRREGULAR2: Float = 0.00f;
    var COTIZACIONES: Float = 0.00f;
    var RED20 : Float = 0.00f;
    var NUMDES: Int = 0
    var MINPER : Float = 0.00f;
    var MINCON : Float = 0.00f;
    var MINDESG : Float = 0.00f;
    var MINDES3 : Float = 0.00f;
    var MINDES : Float = 0.00f;
    var DISPER : Float = 0.00f;
    var ASISPER : Float = 0.00f;
    var MINDISC : Float = 0.00f;
    var MDISDEAS : Float = 0.00f;
    var MINDIS : Float = 0.00f;
    var MINAS : Float = 0.00f;



    fun Calc ():Float {
        getDescendant()
        getRetribution()
        getContribution()
        getDeductions()
        getBase()
        if (!getExent()){
            getQuota()
            getApliLimit43()
        }
        getRetention()
        return TIPO
    }

    private fun getRetribution(){
        RETRIB = Perceptor.ImporteBruto
    }

    private fun getContribution (){
        COTIZACIONES = CalcContribution.Calc(true)
    }

    private fun getDeductions(){
        getOtherExpenses()
        getExpensesDeductions()
        getNetRetribution()
        getReductionRetributionJob()
        getDescendentReduction()
        getMinPersonalFamiliar()
    }

    private fun getMinPersonalFamiliar(){
        getMinTaxpayer()
        getMinDescendens()
        getMinDisability()
        MINDIS = MINDISC + MDISDEAS
        MINPERFA = MINCON+MINDES+MINDIS+MINAS
    }

    private fun getMinDescendens(){
        getMinDesc25()
        getMinDesc3()
        MINDES = MINDESG + MINDES3
    }

    private fun getMinDisability(){
        MINDISC = 0f
        getMinDisabilityTaxpayer()
        getMinDisAsist()
        MINDISC = ASISPER + DISPER
    }

    private fun getMinDisabilityTaxpayer() {
        when (Perceptor.EscalaDiscapacidad ) {
            3 -> DISPER = ConfigIRPF.MinDis65
            2 -> DISPER = ConfigIRPF.MinDis3365
            else -> DISPER = 0f
        }
    }

    private fun getMinDisAsist() {
        ASISPER = 0f
        if (Perceptor.EscalaDiscapacidad == 3 || (Perceptor.EscalaDiscapacidad == 2 && Perceptor.MovilPerceptor) ) {
            ASISPER = ConfigIRPF.MinAsisPer
        }
    }

    private fun getReductionRetributionJob(){
        getDeductionGeneral()
        getNetRetributionReduced()
    }

    private fun getOtherExpenses(){
        getGeneralExpenses()
        getEmployeeDisability()
        getTotalOtherExpenses()

    }

    private fun getMinTaxpayer(){
        MINPER = ConfigIRPF.MinTaxpayer
        MINCON = MINPER
    }

    private fun getMinDesc25(){
        MINDESG = 0f
        var topDesc: Int = 16
        if (Perceptor.NumDescendientesMayores3<topDesc){
            topDesc = Perceptor.NumDescendientesMayores3
        }
        for (i in 1..topDesc){
            when (i){
                1 -> MINDESG = IRPFDesc.Descendants.get(0).Amount
                2 -> MINDESG = MINDESG + IRPFDesc.Descendants.get(1).Amount
                3 -> MINDESG = MINDESG +  IRPFDesc.Descendants.get(2).Amount
                else -> MINDESG = MINDESG +  IRPFDesc.Descendants.get(3).Amount
            }
        }
        MINDESG = getRound(MINDESG)
    }

    private fun getMinDesc3(){
        MINDES3 = 0f
        var topDesc: Int = Perceptor.NumDescendientesMayores3
        for (i in 1..topDesc){
            MINDES3 = MINDES3 + ConfigIRPF.MinDis3
        }
        MINDES3 = getRound(MINDES3)
    }

    private fun getMinoPago () {
        if (RETRIB < ConfigIRPF.HomeDeductionsMaxAmount && Perceptor.ReduccionVivienda == true)
            MINOPAGO = getRound(Perceptor.ImporteBruto * ConfigIRPF.HomeDeductionPercentage)
        else
            MINOPAGO = 0f
    }

    private fun getRetention(){
        getMinoPago()
        getPercRetention()
    }

    private fun getPercRetention (){
        var dif: Float = 0f
        dif = CUOTA-MINOPAGO
        if (dif<0) {
            dif = 0f
        }
        TIPO = dif/RETRIB
        TIPO = getRound(TIPO)
    }

    private fun getRound (Importe :Float):Float{
        var ImporteInt: Int = Math.round(Importe*100)
        var ImporteFloat: Float = (ImporteInt/100f)
        return ImporteFloat
    }

    private fun getApliLimit43 (){

        var Limit :Float = 0.00f;
        if (RETRIB <= ConfigIRPF.AnnualLimitMax) {
            when (Perceptor.SituacionFamiliar) {
                1 -> {
                    if (NUMDES == 1)
                        Limit = (RETRIB - (IRPFQLimits.FamilySituations.get(0).Desc1)) * 0.43f
                    else
                        if (NUMDES > 1)
                            Limit = (RETRIB - (IRPFQLimits.FamilySituations.get(0).Desc2)) * 0.43f
                }
                2 -> {
                    if (NUMDES == 0)
                        Limit = (RETRIB - (IRPFQLimits.FamilySituations.get(1).Desc0)) * 0.43f
                    else {
                        if (NUMDES == 1)
                            Limit = (RETRIB - (IRPFQLimits.FamilySituations.get(1).Desc1)) * 0.43f
                        else
                            if (NUMDES > 1)
                                Limit = (RETRIB - (IRPFQLimits.FamilySituations.get(1).Desc2)) * 0.43f
                    }
                }
                else -> {
                    if (NUMDES == 0)
                        Limit = (RETRIB - (IRPFQLimits.FamilySituations.get(2).Desc0)) * 0.43f
                    else {
                        if (NUMDES == 1)
                            Limit = (RETRIB - (IRPFQLimits.FamilySituations.get(2).Desc1)) * 0.43f
                        else
                            if (NUMDES > 1)
                                Limit = (RETRIB - (IRPFQLimits.FamilySituations.get(2).Desc2)) * 0.43f
                    }
                }
            }
            if ( CUOTA > Limit )
                CUOTA=Limit
        }
    }

    private fun getExent(): Boolean {
        var Exento:Boolean = false;
        if (RETRIB <= IRPFQLimits.FamilySituations.get(1).Desc2){
            when (Perceptor.SituacionFamiliar) {
                1 ->{
                    if ((NUMDES == 1) && (RETRIB <= IRPFQLimits.FamilySituations.get(0).Desc1)){
                        Exento = true
                    } else {
                        if ((NUMDES > 1) && (RETRIB <= IRPFQLimits.FamilySituations.get(0).Desc2))
                            Exento = true
                    }
                }
                2 -> {
                    if ((NUMDES == 0) && (RETRIB <= IRPFQLimits.FamilySituations.get(1).Desc0)){
                        Exento = true
                    } else {
                        if ((NUMDES == 1) && (RETRIB <= IRPFQLimits.FamilySituations.get(1).Desc1)) {
                            Exento = true
                        }else{
                            if ((NUMDES > 1) && (RETRIB <= IRPFQLimits.FamilySituations.get(1).Desc2))
                                Exento = true
                        }
                    }
                }
                3 -> {
                    if ((NUMDES == 0) && (RETRIB <= IRPFQLimits.FamilySituations.get(2).Desc0)){
                        Exento = true
                    } else {
                        if ((NUMDES == 1) && (RETRIB <= IRPFQLimits.FamilySituations.get(2).Desc1)) {
                            Exento = true
                        }else{
                            if ((NUMDES > 1) && (RETRIB <= IRPFQLimits.FamilySituations.get(2).Desc2))
                                Exento = true
                        }
                    }
                }
            }
        }
        if (Exento == true) {
            CUOTA = 0.00f
            TIPO = 0.00f
            return true
        }else
            return false
    }

    private fun getQuota (){
        CUOTA1 = Scale(BASE, 1)
        CUOTA2 = Scale(MINPERFA, 1)
        if (CUOTA1 > CUOTA2){
            CUOTA = CUOTA1-CUOTA2
        }else
        {
            CUOTA = 0.00f
        }
    }

    private fun Scale (Amount: Float, Index: Int): Float {
        var ResultCuota: Float = 0.00f;
        var Dif: Float = 0.00f;
        var ScaleQuota: Float = 0.00f;
        var DifQuota: Float = 0.00f;
        if (Amount>IRPFQLimits.ScalePercentages.get(Index).Base){
            ResultCuota = Scale(Amount,(Index+1))
        }else{
            ScaleQuota = IRPFQLimits.ScalePercentages.get(Index-1).Withholding
            Dif = Amount-IRPFQLimits.ScalePercentages.get(Index-1).Base
            DifQuota = Dif * IRPFQLimits.ScalePercentages.get(Index-1).Percentage
            ResultCuota = ScaleQuota+DifQuota
        }
        return  ResultCuota
    }

    private fun getBase(){
        REDU = PENSION+HIJOS+DESEM+CONYUGE
        if (RNTREDU>REDU){
            BASE = RNTREDU-REDU
        }else{
            BASE=0.00f
        }
    }

    private fun getGeneralExpenses (){
        GASTOSGEN = ConfigIRPF.ExpensesGeneral
    }

    private fun getEmployeeDisability (){
        // 1: Disc32, 2: Disc33a65, 3: Disc66
        if((Perceptor.EscalaDiscapacidad == 3) || (Perceptor.EscalaDiscapacidad == 2 && (Perceptor.MovilPerceptor == true))){
            INCREGASDISTRA = ConfigIRPF.DisAmountMore65
        }else{
            INCREGASDISTRA = ConfigIRPF.DisWithoutHelp
        }
    }

    private fun getTotalOtherExpenses () {
        OTRASGASTOS = GASTOSGEN+INCREGASDISTRA+INCREGASMOVIL
        if ((RETRIB-COTIZACIONES) < 0){
            OTRASGASTOS = 0.00f
        }
        if (OTRASGASTOS > (RETRIB - COTIZACIONES)){
            OTRASGASTOS = RETRIB - COTIZACIONES
        }
    }

    private fun getExpensesDeductions(){
        GASTOS = COTIZACIONES+OTRASGASTOS
    }

    private fun getNetRetribution (){
        RNT = RETRIB-(IRREGULAR1+IRREGULAR2+COTIZACIONES)
        if (RNT<0) {
            RNT = 0.00f
        }
    }

    private fun getDeductionGeneral(){
        if(RNT<=ConfigIRPF.BaseRedJobMax){
            RED20 = ConfigIRPF.RedJobMax
        }else{
            if (RNT<=ConfigIRPF.BaseRedJobMin){
                RED20 = getRound(ConfigIRPF.RedJobMin - (ConfigIRPF.CoefRedJob * (RNT-ConfigIRPF.RedJobMax)))
            } else {
                RED20 = 0.00f
            }
        }
    }

    private fun getNetRetributionReduced (){
        RNTREDU = RNT - (OTRASGASTOS + RED20)
        if (RNTREDU < 0){
            RNTREDU = 0f
        }
    }

    private fun getDescendentReduction () {

        if (NUMDES>2){
            HIJOS = IRPFDesc.More2Desc
        }else{
            HIJOS = 0f
        }
    }

    private fun getDescendant(){
        NUMDES = Perceptor.NumDescendientesMayores3 + Perceptor.NumDescendientesMenores3
    }

}
