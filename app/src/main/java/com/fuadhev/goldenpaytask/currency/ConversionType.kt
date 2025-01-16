package com.fuadhev.goldenpaytask.currency

enum class ConversionType (val currency:String, val conversionRate: Double) {
    AZN("AZN",1.0),
    USD("USD",1.7),
    EURO("EUR",1.81),
    RUBLE("RUB",60.36);

}
