package com.inc.lite.stationdemo.ui.navigation

sealed class Screen(val route: String){

    // Home screens
    object Main: Screen("main_screen")
    object Programs: Screen("programs_screen")
    object WebView: Screen("webview_screen")


    //Renting screens
    object RegOrLogin: Screen("reg_or_login_screen")
    //Login
    object LoginEnterNumber: Screen("log_number_screen")
    object LoginScreen: Screen("login_screen")
    object LoginEnterSMS: Screen("log_sms_screen")
    object LoginEnterPass: Screen("log_pass_screen")
    object LoginCreatePass: Screen("log_create_pass_screen")

    //Registration
    object Registration: Screen("registration_screen")
    object RegEnterEmail: Screen("reg_email_screen")
    object RegEnterNickName: Screen("reg_nick_name_screen")


    //Rent
    object StartRent: Screen("start_rent_screen")
    object ChoosePaymentMethod: Screen("choose_payment_method_screen")

    object AskCoupons: Screen("ask_coupons")
    object Coupons: Screen("coupons")
    object EnterManuallyCoupons: Screen("enter_manually_coupons")
    object ChoseWitchCoupons: Screen("chose_witch_coupons")


    //Account
    object  ProfilePage: Screen("profile_page_screen")


}
