package com.example.raulcorchero.grosscleaner

class Configuration_IRPF () {
    val Year: Int = 2016
    val AnnualLimitMax: Float = 22000.00f
    val QuoteLimitMax: Int = 43
    val DisAmountMore65: Float = 7750.00f
    val DisWithoutHelp: Float = 3500f
    val HomeDeductionsMaxAmount: Float = 33007.20f
    val HomeDeductionPercentage: Int = 2
    val ExpensesGeneral: Float = 2000.00f

    val QuantitativeLimits: Configuration_IRPF_QuantitativeLimits = Configuration_IRPF_QuantitativeLimits()
    val Descendants: Configuration_IRPF_Descendants = Configuration_IRPF_Descendants()

}