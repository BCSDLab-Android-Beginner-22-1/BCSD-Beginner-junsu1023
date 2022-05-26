package com.example.bcsdassign4

import android.app.*
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import org.w3c.dom.Text

const val NOTIFICATION_ID = 1004
const val CHANNEL_ID = "my_channel"
private var count = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dialogButton: Button = findViewById(R.id.dialog_button)
        val countButton: Button = findViewById(R.id.count_button)
        val randomButton: Button = findViewById(R.id.random_button)
        val numberText: TextView = findViewById(R.id.number_text)

        dialogButton.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.three_to_one))
                .setMessage(getString(R.string.select_button))
                .setPositiveButton(getString(R.string.reset)) { _, _->
                    count = 0
                    numberText.text = count.toString()
                }
                .setNeutralButton(getString(R.string.toast)) { _, _ ->
                    Toast.makeText(this, getString(R.string.surprise), Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton(getString(R.string.End)) { dialog, _ ->
                    dialog.dismiss()
                }
            builder.show()
        })

        countButton.setOnClickListener {
            count++
            numberText.text = "$count"
        }

        createNotificationChannel()
        randomButton.setOnClickListener {
            displayNotification(CHANNEL_ID)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.Channel_name)
            val descriptionText = getString(R.string.Channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun displayNotification(channelId: String) {
        val intent = Intent(this, ChangeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("count", count)
        }

        val pendingIntent: PendingIntent =
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
            }
        else {
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            }

        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(getString(R.string.Channel_name))
            .setContentText(getString(R.string.Channel_description))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_ID, builder.build())
        }
    }
}