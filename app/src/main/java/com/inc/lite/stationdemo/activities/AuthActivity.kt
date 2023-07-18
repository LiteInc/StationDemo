package com.inc.lite.stationdemo.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import com.inc.lite.stationdemo.modules.navigation.AuthNavGraph
import com.inc.lite.stationdemo.modules.theme.StationLiteTheme
import com.inc.lite.stationdemo.util.FullScreenMode
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FullScreenMode().hideSystemUI(window)
        setContent {
            StationLiteTheme {
                val navHostController = rememberNavController()
               AuthNavGraph(navHostController = navHostController)
            }
        }

    }
}