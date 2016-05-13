package com.example.raulcorchero.grosscleaner

class Configuration_IRPF_QuantitativeLimits_ScalePercentages {
    var Base: Float = 0f
    var Withholding: Float = 0f
    var Percentage: Float = 0.00f

    constructor (_Base: Float, _Withholding: Float, _Percentage: Float) {
        Base = _Base
        Withholding = _Withholding
        Percentage = _Percentage
    }
}