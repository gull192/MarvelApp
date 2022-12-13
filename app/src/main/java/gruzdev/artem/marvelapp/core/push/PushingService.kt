package gruzdev.artem.marvelapp.core.push

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import gruzdev.artem.marvelapp.MainActivity
import gruzdev.artem.marvelapp.R
import gruzdev.artem.marvelapp.screens.destinations.PersonScreenDestination

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class PushingService  : FirebaseMessagingService() {

    override fun onMessageReceived (remoteMessage: RemoteMessage) {
        val messageTitle = remoteMessage.notification?.title
        val messageBody = remoteMessage.notification?.body
        val heroId = remoteMessage.data["hero_id"]?.toInt()
        Log.e("MESSAGE", "message")
        if (messageTitle != null && messageBody != null ) {
            sendNotification(
                messageTitle = messageTitle,
                messageBody = messageBody,
                id = heroId!!
            )
        }
        super.onMessageReceived(remoteMessage)
    }

    private fun sendNotification(
        messageTitle: String,
        messageBody: String,
        id: Int
    ) {
        val validUserScreenRoute = PersonScreenDestination(characterId = id).route
        val uriForPersonScreen = "https://myapp.com/$validUserScreenRoute".toUri()
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val flag = PendingIntent.FLAG_IMMUTABLE

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                "my_notification",
                "n_channel",
                NotificationManager.IMPORTANCE_UNSPECIFIED
            )
            notificationChannel.description = "description"
            notificationChannel.name = "Channel Name"
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val clickIntent = Intent(
            Intent.ACTION_VIEW,
            uriForPersonScreen,
            this,
            MainActivity::class.java
        ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)

        val clickPendingIntent: PendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(clickIntent)
            getPendingIntent(1, flag)
        }

        val notificationBuilder = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.small_marvel_logo)
            .setChannelId("my_notification")
            .setContentTitle(messageTitle)
            .setContentText(messageBody)
            .setAutoCancel(true)
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(clickPendingIntent)

        notificationManager.notify(1, notificationBuilder.build())
    }

}
