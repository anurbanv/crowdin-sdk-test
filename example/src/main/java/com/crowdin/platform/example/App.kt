package com.crowdin.platform.example

import android.app.Application
import android.content.res.Configuration
import com.crowdin.platform.Crowdin
import com.crowdin.platform.CrowdinConfig
import com.crowdin.platform.data.model.AuthConfig
import com.crowdin.platform.data.remote.NetworkType
import com.crowdin.platform.example.utils.updateLocale

class App : Application() {

    lateinit var languagePreferences: LanguagePreferences

    override fun onCreate() {
        super.onCreate()
        languagePreferences = LanguagePreferences(this)

        // Crowdin sdk initialization
        val distributionHash = "e-b607598f90e3e8e4643d3b2t36"     // "7a0c1...7uo3b"
        val networkType = NetworkType.ALL                  // ALL, CELLULAR, WIFI
        val intervalInSeconds: Long = 18 * 60               // 18 minutes, min 15 min

        // Set custom locale before SDK initialization.
        this.updateLocale(languagePreferences.getLanguageCode())
        Crowdin.init(
            applicationContext,
            CrowdinConfig.Builder()
                .withDistributionHash(distributionHash)                                 // required
                .withNetworkType(networkType)                                           // optional
                .withUpdateInterval(intervalInSeconds)                                  // optional                 // optional
                .build()
        )
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Reload translations on configuration change when real-time preview ON.
        Crowdin.onConfigurationChanged()
    }
}
