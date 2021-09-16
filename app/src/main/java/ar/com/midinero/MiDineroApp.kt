package ar.com.midinero

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MiDineroApp: Application(){
    companion object {
        const val TAG = "MiDineroApp/"
    }
}