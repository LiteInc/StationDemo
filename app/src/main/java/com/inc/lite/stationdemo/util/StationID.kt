package com.inc.lite.stationdemo.util

/**
 * Utility class for manipulating station IDs.
 *
 * @author Nik4Prod
 */
class StationID {

    /**
     * Retrieves the user-friendly station ID from the provided IMEI.
     *
     * @param imei The IMEI from which to derive the station ID.
     * @return The user-friendly station ID.
     */
    fun getUserFriendlyStationId(imei: String): String{
        var result  = "" //867329045463077 86.73.29.04.54.63.07.7  867329 - TAC, 04 - FAC, 546307 - SNR, 7 - SP UserFriendlyId - 86546307
        val output = imei.toCharArray()
        for(n in output.indices){
            when(n){
                0,1,8,9,10,11,12,13  -> {
                    result += output[n].toString()
                }
                else -> {}
            }
        }
        return result
   }
}