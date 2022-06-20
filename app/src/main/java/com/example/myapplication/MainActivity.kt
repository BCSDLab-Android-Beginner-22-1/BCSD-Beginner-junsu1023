package com.example.myapplication

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URI
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        when {
            isGranted -> startProcess()
            else -> permissionDialog()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermission()
    }

    private fun startProcess() {
        val adapter = MusicAdapter()
        adapter.musicList.addAll(getMusicList())

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
    }

    private fun getMusicList(): List<MusicData> {
        val musicListUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.DURATION)
        val cursor = contentResolver.query(musicListUri, projection, null, null, null)
        val musicList = mutableListOf<MusicData>()

        while(cursor?.moveToNext() == true) {
            val id = cursor.getString(0)
            val title = cursor.getString(1)
            val artist = cursor.getString(2)
            val duration = cursor.getLong(3)


            val music = MusicData(id, title, artist, duration)
            musicList.add(music)
        }
        return musicList
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkPermission() {
        requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun permissionDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.permission_dialog_title))
            .setMessage(getString(R.string.permission_dialog_message))
            .setPositiveButton(getString(R.string.permission_dialog_allow)) { _, _ ->
                checkPermission()
            }
            .setNegativeButton(getString(R.string.permission_dialog_no)) { dialog, _ ->
                dialog.dismiss()
                finish()
            }
        builder.show()
    }
}