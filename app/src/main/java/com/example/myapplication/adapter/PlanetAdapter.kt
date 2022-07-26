package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.PlanetData

class PlanetAdapter: RecyclerView.Adapter<PlanetAdapter.ViewHolder>() {
    var planetItems = ArrayList<PlanetData>()

    fun setUpdatedPlanet(items : ArrayList<PlanetData>) {
        this.planetItems = items
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val planetName: TextView = view.findViewById(R.id.planet_name)
        private val planetPopulation: TextView = view.findViewById(R.id.planet_population)

        fun bind(data : PlanetData) {
            planetName.text = data.name
            planetPopulation.text = data.population
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_planet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(planetItems[position])
    }

    override fun getItemCount(): Int = planetItems.size
}