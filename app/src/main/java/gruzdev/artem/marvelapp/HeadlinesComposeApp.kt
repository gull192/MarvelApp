package gruzdev.artem.marvelapp

import android.app.Application
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class HeadlinesComposeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@addOnCompleteListener
            }

            val token = task.result
            Log.e("TOKEN", "token -> $token")
        }
    }
}
