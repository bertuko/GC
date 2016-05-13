package com.example.raulcorchero.grosscleaner

/**
 * Created by david.negrete on 12/05/2016.
 */
class Configuration () {
    val IRPF: Configuration_IRPF = Configuration_IRPF();
    val Contribution:  Configuration_Contribution = Configuration_Contribution();

    constructor(bLoadData: Boolean) : this() {
        this.get()
    }

    fun get () {
        IRPF.Year = 2016
        IRPF.AnnualLimitMax = 22000.00f
        IRPF.QuoteLimitMax = 0.43f
        IRPF.DisAmountMore65 = 7750.00f
        IRPF.DisWithoutHelp = 3500.00f
        IRPF.HomeDeductionsMaxAmount = 33007.20f
        IRPF.HomeDeductionPercentage = 0.02f
        IRPF.ExpensesGeneral = 2000.00f
        IRPF.MinTaxpayer = 5550.00f
        IRPF.MinDis65 = 9000.00f
        IRPF.MinDis3365 = 3000.00f
        IRPF.MinAsisPer = 3000.00f

        IRPF.QuantitativeLimits.FamilySituations.add ( Configuration_IRPF_QuantitativeLimits_FamilySituations ( 0.00f, 14266.00f, 15803.00f ) )
        IRPF.QuantitativeLimits.FamilySituations.add ( Configuration_IRPF_QuantitativeLimits_FamilySituations ( 13696.00f, 14985.00f, 17138.00f ) )
        IRPF.QuantitativeLimits.FamilySituations.add ( Configuration_IRPF_QuantitativeLimits_FamilySituations ( 12000.00f, 12607.00f, 13275.00f ) )

        IRPF.QuantitativeLimits.ScalePercentages.add ( Configuration_IRPF_QuantitativeLimits_ScalePercentages ( 0.00f, 0.00f, 19.00f ) )
        IRPF.QuantitativeLimits.ScalePercentages.add ( Configuration_IRPF_QuantitativeLimits_ScalePercentages ( 12450.00f, 2365.50f, 24.00f ) )
        IRPF.QuantitativeLimits.ScalePercentages.add ( Configuration_IRPF_QuantitativeLimits_ScalePercentages ( 20200.00f, 4225.50f, 30.00f ) )
        IRPF.QuantitativeLimits.ScalePercentages.add ( Configuration_IRPF_QuantitativeLimits_ScalePercentages ( 35200.00f, 8725.50f, 37.00f ) )
        IRPF.QuantitativeLimits.ScalePercentages.add ( Configuration_IRPF_QuantitativeLimits_ScalePercentages ( 60000.00f, 17901.50f, 45.00f ) )

        IRPF.Descendants.More2Desc = 600f
        IRPF.Descendants.DescendantMinor3 = 2800.00f
        IRPF.Descendants.Descendants.add ( Configuration_IRPF_Descendants_Descendant ( 1, 2400.00f ) )
        IRPF.Descendants.Descendants.add ( Configuration_IRPF_Descendants_Descendant ( 2, 2400.00f ) )
        IRPF.Descendants.Descendants.add ( Configuration_IRPF_Descendants_Descendant ( 3, 2400.00f ) )
        IRPF.Descendants.Descendants.add ( Configuration_IRPF_Descendants_Descendant ( 4, 2400.00f ) )

        Contribution.Year = 2016

        Contribution.CommonContigencies.Percentage = 0.047f
        Contribution.CommonContigencies.Scale.add ( Configuration_Contribution_CommonContingencies_Scale ( 1, 3642.00f, 764.00f, 4.60f ) )

        Contribution.ProfessionalContingencies.Max = 3642.00f
        Contribution.ProfessionalContingencies.MinMonthly = 764.40f
        Contribution.ProfessionalContingencies.MinHour = 4.60f
        Contribution.ProfessionalContingencies.PercentageDesemployee = 0.0155f
        Contribution.ProfessionalContingencies.PercentageFP = 0.001f
    }
}