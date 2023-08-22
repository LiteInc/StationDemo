package com.inc.lite.stationdemo.model.uiState

import com.inc.lite.stationdemo.model.PaymentCard
import com.inc.lite.stationdemo.model.PaymentType
import com.inc.lite.stationdemo.model.Tariff

data class RentUiState(
    val listOfPayments: List<PaymentCard> = listOf(
        PaymentCard(PaymentType.LiteWallet, isSelected = false),
        PaymentCard(PaymentType.LinePay, isSelected = false),
        PaymentCard(PaymentType.Coupons, isSelected = false)
    ),
    val tariff: Tariff = Tariff.First
)