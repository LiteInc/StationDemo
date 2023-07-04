package com.inc.lite.stationdemo.util

/**
 * Utility class for generating QR code links.
 *
 * @author Nik4Prod
 */
class QrCodeLink {

    /**
     * Generates a QR code link based on the provided IMEI.
     *
     * @param imei The IMEI used to generate the QR code link.
     * @return The generated QR code link.
     */
    fun getLink(imei: String): String {
        return "http://www.riisu.co/rent?now=${StationID().getUserFriendlyStationId(imei)}"
    }
}
