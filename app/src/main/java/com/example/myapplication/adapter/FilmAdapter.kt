package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.FilmData

class FilmAdapter: RecyclerView.Adapter<FilmAdapter.ViewHolder>() {
    private var filmItems = ArrayList<FilmData>()

    fun setUpdatedFilm(items : ArrayList<FilmData>) {
        this.filmItems = items
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val filmTitle: TextView = view.findViewById(R.id.film_title)
        private val filmEpisodeId: TextView = view.findViewById(R.id.film_episodeId)
        private val filmReleaseDate: TextView = view.findViewById(R.id.film_release_date)

        fun bind(data : FilmData) {
            filmTitle.text = data.title
            filmEpisodeId.text = data.episode_id
            filmReleaseDate.text = data.release_date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filmItems[position])
    }

    override fun getItemCount(): Int = filmItems.size
}