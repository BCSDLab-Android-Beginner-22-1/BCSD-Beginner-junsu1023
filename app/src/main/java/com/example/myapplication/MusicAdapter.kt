package com.example.myapplication

import android.net.Uri
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.music_item.view.*
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

class MusicAdapter: RecyclerView.Adapter<MusicAdapter.ViewHolder>() {
    var musicList = mutableListOf<MusicData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.music_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val music = musicList[position]
        holder.setMusic(music)
    }

    override fun getItemCount() = musicList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var musicUri: Uri? = null

        fun setMusic(music: MusicData) {
            musicUri = music.getMusicUri()
            itemView.title_text_view.text = music.title
            itemView.artist_text_view.text = music.artist
            val sdf = SimpleDateFormat("HH:mm:ss")
            itemView.duration_text_view.text = sdf.format(music.duration)
        }
    }
}