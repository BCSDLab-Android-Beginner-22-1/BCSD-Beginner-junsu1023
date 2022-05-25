package com.example.bcsdassign4

import android.app.*
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

const val notificationId = 1002
class MainActivity : AppCompatActivity() {
    private val channelId = "my_channel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var count = 1

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

        countButton.setOnClickListener(View.OnClickListener {
            count++
            numberText.text = "$count"
        })

        randomButton.setOnClickListener {
            displayNotification(count, channelId)
        }
    }

    private fun createNotificationChannel(channelId: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.Channel_name)
            val descriptionText = getString(R.string.Channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun displayNotification(count: Int, channelId: String) {
        val intent = Intent(this, ChangeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        intent.putExtra("count", count)

        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(getString(R.string.Channel_name))
            .setContentText(getString(R.string.Channel_description))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }
    }
}