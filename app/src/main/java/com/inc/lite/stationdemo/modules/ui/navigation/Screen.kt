package com.inc.lite.stationdemo.modules.ui.navigation

sealed class Screen(val route: String){

    // Home screens
    object Main: Screen("main_screen")
    object Programs: Screen("programs_screen")
    object WebView: Screen("webview_screen")


    //Renting screens
    object RegOrLogin: Screen("reg_or_login_screen")
    //Login
    object LoginEnterNumber: Screen("log_number_screen")
    object LoginEnterSMS: Screen("log_sms_screen")
    object LoginEnterPass: Screen("log_pass_screen")

    //Registration
    object RegEnterEmail: Screen("reg_email_screen")


    //Rent
    object Rent: Screen("rent_screen")


    //Account
    object  AccountPage: Screen("account_page_screen")


}
