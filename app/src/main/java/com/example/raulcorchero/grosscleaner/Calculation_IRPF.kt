package com.example.raulcorchero.grosscleaner

import java.text.DecimalFormat


/**
 * Created by raul.palomares on 12/05/2016.
 */
class Calculation_IRPF () {
    var MINOPAGO: Float = 0.00f;
    var MINPERFA: Float = 0.00f;
    var CUOTA: Float = 0.00f;
    var CUOTA1: Float = 0.00f;
    var CUOTA2: Float = 0.00f;
    var TIPO: Float = 0.00f;
    var ConfigIRPF: Configuration_IRPF = Configuration_IRPF();
    var IRPFDesc: Configuration_IRPF_Descendants = Configuration_IRPF_Descendants();
    var IRPFDescsDesc: Configuration_IRPF_Descendants_Descendant = Configuration_IRPF_Descendants_Descendant();
    var IRPFQLimits: Configuration_IRPF_QuantitativeLimits = Configuration_IRPF_QuantitativeLimits();
    var IRPFQLimitsFS: Configuration_IRPF_QuantitativeLimits_FamilySituations = Configuration_IRPF_QuantitativeLimits_FamilySituations();
    var IRPFQLimitsSP: Configuration_IRPF_QuantitativeLimits_ScalePercentages = Configuration_IRPF_QuantitativeLimits_ScalePercentages();
    var Perceptor: User = User();
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

    fun getMinoPago () {
        if (Perceptor.ImporteBruto < ConfigIRPF.HomeDeductionsMaxAmount && Perceptor.ReduccionVivienda == true)
            MINOPAGO = Perceptor.ImporteBruto * ConfigIRPF.HomeDeductionPercentage
        else
            MINOPAGO = 0f

    }
    fun getTipoRetencion (){

        TIPO = (CUOTA-MINOPAGO)/Perceptor.ImporteBruto
        var TIPOI: Int =Math.round(TIPO*100);
        TIPO = (TIPOI/100f)
    }
    fun ApliLimit43 (){
        var NumDesc :Int = 0;
        NumDesc =0// getNumDescendent()
        var Limit :Float = 0.00f;
        if (Perceptor.ImporteBruto <= ConfigIRPF.AnnualLimitMax) {
            when (Perceptor.SituacionFamiliar) {
                1 -> {
                    if (NumDesc == 1)
                        Limit = (Perceptor.ImporteBruto - (IRPFQLimits.FamilySituations.get(0).Desc1)) * 0.43f
                    else
                        if (NumDesc > 1)
                            Limit = (Perceptor.ImporteBruto - (IRPFQLimits.FamilySituations.get(0).Desc2)) * 0.43f
                }
                2 -> {
                    if (NumDesc == 0)
                        Limit = (Perceptor.ImporteBruto - (IRPFQLimits.FamilySituations.get(1).Desc0)) * 0.43f
                    else {
                        if (NumDesc == 1)
                            Limit = (Perceptor.ImporteBruto - (IRPFQLimits.FamilySituations.get(1).Desc1)) * 0.43f
                        else
                            if (NumDesc > 1)
                                Limit = (Perceptor.ImporteBruto - (IRPFQLimits.FamilySituations.get(1).Desc2)) * 0.43f
                    }
                }
                else -> {
                    if (NumDesc == 0)
                        Limit = (Perceptor.ImporteBruto - (IRPFQLimits.FamilySituations.get(2).Desc0)) * 0.43f
                    else {
                        if (NumDesc == 1)
                            Limit = (Perceptor.ImporteBruto - (IRPFQLimits.FamilySituations.get(2).Desc1)) * 0.43f
                        else
                            if (NumDesc > 1)
                                Limit = (Perceptor.ImporteBruto - (IRPFQLimits.FamilySituations.get(2).Desc2)) * 0.43f
                    }
                }
            }
            if ( CUOTA > Limit )
                CUOTA=Limit
        }
    }

    fun getExent() {
        var NumDesc :Int = 0;
        var Exento:Boolean = false;
        NumDesc =0// getNumDescendent()
        if (Perceptor.ImporteBruto <= IRPFQLimits.FamilySituations.get(1).Desc2){
            when (Perceptor.SituacionFamiliar) {
                1 ->{
                    if ((NumDesc == 1) and (Perceptor.ImporteBruto <= IRPFQLimits.FamilySituations.get(0).Desc1)){
                        Exento = true
                    } else {
                        if ((NumDesc > 1) and (Perceptor.ImporteBruto <= IRPFQLimits.FamilySituations.get(0).Desc2))
                            Exento = true
                    }
                }
                2 -> {
                    if ((NumDesc == 0) and (Perceptor.ImporteBruto <= IRPFQLimits.FamilySituations.get(1).Desc0)){
                        Exento = true
                    } else {
                        if ((NumDesc == 1) and (Perceptor.ImporteBruto <= IRPFQLimits.FamilySituations.get(1).Desc1)) {
                            Exento = true
                        }else{
                            if ((NumDesc > 1) and (Perceptor.ImporteBruto <= IRPFQLimits.FamilySituations.get(1).Desc2))
                                Exento = true
                        }
                    }
                }
                3 -> {
                    if ((NumDesc == 0) and (Perceptor.ImporteBruto <= IRPFQLimits.FamilySituations.get(2).Desc0)){
                        Exento = true
                    } else {
                        if ((NumDesc == 1) and (Perceptor.ImporteBruto <= IRPFQLimits.FamilySituations.get(2).Desc1)) {
                            Exento = true
                        }else{
                            if ((NumDesc > 1) and (Perceptor.ImporteBruto <= IRPFQLimits.FamilySituations.get(2).Desc2))
                                Exento = true
                        }
                    }
                }
            }
        }
        if (Exento == true) {
            CUOTA = 0.00f
            TIPO = 0.00f
        }
    }
    fun getQuota (){
        CUOTA1 = Scale(BASE, 1)
        CUOTA2 = Scale(MINPERFA, 1)
        if (CUOTA1 > CUOTA2){
            CUOTA = CUOTA1-CUOTA2
        }else
        {
            CUOTA = 0.00f
        }
    }
    fun Scale (Amount: Float, Index: Int): Float {
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
    fun getBase(){
        REDU = PENSION+HIJOS+DESEM+CONYUGE
        if (RNTREDU>REDU){
            BASE = RNTREDU-REDU
        }else{
            BASE=0.00f
        }
    }
    fun getGeneralExpenses (){
        GASTOSGEN = ConfigIRPF.ExpensesGeneral
    }

    fun getEmployeeDisability (){
        if(Perceptor.EscalaDiscapacidad == Discapacidad.Disc66 or (Perceptor.EscalaDiscapacidad == Discapacidad.Disc33a65 and Perceptor.MovilPer == true)){
            INCREGASDISTRA = ConfigIRPF.DisAmountMore65
        }else{
            INCREGASDISTRA = ConfigIRPF.DisWithoutHelp
        }
    }
    fun getTotalOtherExpenses () {
        OTRASGASTOS = GASTOSGEN+INCREGASDISTRA+INCREGASMOVIL
        if ((Perceptor.ImporteBruto-COTIZACIONES) < 0){
            OTRASGASTOS = 0.00f
        }
        if (OTRASGASTOS > (Perceptor.ImporteBruto - COTIZACIONES)){
            OTRASGASTOS = Perceptor.ImporteBruto - COTIZACIONES
        }
    }
    fun getDeductions(){
        GASTOS = COTIZACIONES+OTRASGASTOS
    }
    fun getNetRetribution (){
        RNT = RETRIB-(IRREGULAR1+IRREGULAR2+COTIZACIONES)
        if (RNT<0) {
            RNT = 0.00f
        }
    }
    fun getDeductionGeneral(){
        if(RNT<=ConfigIRPF.BaseRedJobMax){
            RED20 = ConfigIRPF.RedJobMax
        }else{
            if (RNT<=ConfigIRPF.BaseRedJobMin){
                RED20 = (ConfigIRPF.RedJobMin - (ConfigIRPF.CoefRedJob * (RNT-ConfigIRPF.RedJobMax)))
            } else {
                RED20 = 0.00f
            }
        }
    }
}
