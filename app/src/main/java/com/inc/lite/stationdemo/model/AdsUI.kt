package com.inc.lite.stationdemo.model

data class AdsUI(
    val adsLayout: AdsLayouts = AdsLayouts.SingleImage,
    val adsList: List<AdsItem> = listOf(),
    val isAdsLoaded: Boolean = false
)
data class AdsItem(
    val url: String = "",
    val type: AdsType = AdsType.Image,
    val playTime: Long = 1000,
    val order: Int = 0
)

enum class AdsType{
    Video,
    Image
}
