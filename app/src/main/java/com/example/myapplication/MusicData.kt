package com.example.myapplication

import android.net.Uri
import android.provider.MediaStore

class MusicData(id: String, title: String, artist: String, duration: Long) {
    var id = ""
    var title = ""
    var artist = ""
    var duration = 0L

    init {
        this.id = id
        this.title = title
        this.artist = artist
        this.duration = duration
    }

    fun getMusicUri(): Uri = Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)
}
