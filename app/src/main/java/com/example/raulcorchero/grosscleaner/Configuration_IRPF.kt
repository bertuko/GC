package com.example.raulcorchero.grosscleaner

class Configuration_IRPF () {
    var Year: Int = 2016
    var AnnualLimitMax: Float = 22000.00f
    var QuoteLimitMax: Float = 0.43f
    var DisAmountMore65: Float = 7750.00f
    var DisWithoutHelp: Float = 3500f
    var HomeDeductionsMaxAmount: Float = 33007.20f
    var HomeDeductionPercentage: Float = 0.2f
    var ExpensesGeneral: Float = 2000.00f
    var BaseRedJobMax: Float = 11250.00f
    var RedJobMax: Float = 3700.00f
    var BaseRedJobMin: Float = 14450.00f
    var RedJobMin: Float = 0.00f
    var CoefRedJob = 1.15625f
    var MinTaxpayer = 5550.00f
    var MinDis65 = 9000.00f
    var MinDis3365 = 3000.00f
    var MinAsisPer = 3000.00f
    var MinDis3 = 2800.00f

    var QuantitativeLimits: Configuration_IRPF_QuantitativeLimits = Configuration_IRPF_QuantitativeLimits()
    var Descendants: Configuration_IRPF_Descendants = Configuration_IRPF_Descendants()



}