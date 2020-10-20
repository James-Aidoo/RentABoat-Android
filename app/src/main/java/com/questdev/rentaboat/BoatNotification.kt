package com.questdev.rentaboat

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build

import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder

/**
 * Helper class for showing and canceling boat
 * notifications.
 *
 *
 * This class makes heavy use of the [NotificationCompat.Builder] helper
 * class to create notifications in a backward-compatible way.
 */
object BoatNotification {
    /**
     * The unique identifier for this type of notification.
     */
    private val NOTIFICATION_TAG = "Boat"

    /**
     * Shows the notification, or updates a previously shown notification of
     * this type, with the given parameters.
     *
     *
     * TODO: Customize this method's arguments to present relevant content in
     * the notification.
     *
     *
     * TODO: Customize the contents of this method to tweak the behavior and
     * presentation of boat notifications. Make
     * sure to follow the
     * [
 * Notification design guidelines](https://developer.android.com/design/patterns/notifications.html) when doing so.
     *
     * @see .cancel
     */
    fun notify(context: Context) {
        val res = context.resources

        // This image is used as the notification's large icon (thumbnail).
        // TODO: Remove this if your notification has no relevant thumbnail.
        val picture = BitmapFactory.decodeResource(res, R.drawable.speed_boat_blue)


        val title = "Now on Sale!" /*res.getString(
            R.string.boat_notification_title_template, exampleString
        )*/
        val text = "This could be your chance to ride this awesome boat. Rent it Now!" /*res.getString(
            R.string.boat_notification_placeholder_text_template, exampleString
        )*/
        val args = BoatFragmentArgs.Builder(3).build().toBundle()
        val pendingIntent= NavDeepLinkBuilder(context)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.boatFragment)
            .setArguments(args)
            .createPendingIntent()

        val builder = NotificationCompat.Builder(context, "Boat")

            // Set appropriate defaults for the notification light, sound,
            // and vibration.
            .setDefaults(Notification.DEFAULT_ALL)

            // Set required fields, including the small icon, the
            // notification title, and text.
            .setSmallIcon(R.drawable.ic_stat_boat)
            .setContentTitle(title)
            .setContentText(text)

            // All fields below this line are optional.

            // Use a default priority (recognized on devices running Android
            // 4.1 or later)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            // Provide a large icon, shown with the notification in the
            // notification drawer on devices running Android 3.0 or later.
            .setLargeIcon(picture)

            // Set ticker text (preview) information for this notification.
            .setTicker(title)


            // Show a number. This is useful when stacking notifications of
            // a single type.
//            .setNumber(number)

            // If this notification relates to a past or upcoming event, you
            // should set the relevant time information using the setWhen
            // method below. If this call is omitted, the notification's
            // timestamp will by set to the time at which it was shown.
            // TODO: Call setWhen if this notification relates to a past or
            // upcoming event. The sole argument to this method should be
            // the notification timestamp in milliseconds.
            //.setWhen(...)

            // Set the pending intent to be initiated when the user touches
            // the notification.
            .setContentIntent(pendingIntent)

            // Automatically dismiss the notification when it is touched.
            .setAutoCancel(true)

        notify(context, builder.build())
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private fun notify(context: Context, notification: Notification) {
        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                val channel = NotificationChannel("Boat", "Boat", NotificationManager.IMPORTANCE_DEFAULT)
                nm.createNotificationChannel(channel)
                nm.notify(NOTIFICATION_TAG, 0, notification)
            }
            else -> nm.notify(NOTIFICATION_TAG.hashCode(), notification)
        }
    }

    /**
     * Cancels any notifications of this type previously shown using
     * [.notify].
     */
    @TargetApi(Build.VERSION_CODES.ECLAIR)
    fun cancel(context: Context) {
        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.cancel(NOTIFICATION_TAG, 0)
        } else {
            nm.cancel(NOTIFICATION_TAG.hashCode())
        }
    }
}
