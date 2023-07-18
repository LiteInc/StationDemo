package com.inc.lite.stationdemo.modules.models

data class AdsUI(
    val adsLayout: AdsLayouts = AdsLayouts.SingleImage,
    val adsList: List<AdsItem> = listOf(AdsItem("http://res.cloudinary.com/riisu/video/upload/v1689624700/c7hcaxqt09q2mlv7efs4.mp4", AdsType.Video))
)
data class AdsItem(
    val url: String = "",
    val type: AdsType = AdsType.Image
)

enum class AdsType{
    Video,
    Image
}
