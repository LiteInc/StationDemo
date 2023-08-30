package com.inc.lite.stationdemo.model

data class AdsUI(
    val adsLayout: AdsLayouts = AdsLayouts.SingleImage,
    val adsList: List<AdsItem> = listOf(
        AdsItem("https://res.cloudinary.com/riisu/image/upload/v1692865710/dva8rn2ltaju0artrisr.jpg",AdsType.Image, 5000),
    ),
    val isAdsLoaded: Boolean = true
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
