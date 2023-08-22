package com.inc.lite.stationdemo.model

sealed class PaymentType(
    val name: String,
){
    object LiteWallet : PaymentType("lite_wallet")
    object LinePay : PaymentType("line_pay")
    object Coupons : PaymentType("coupons")
}
