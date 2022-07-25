package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.PeopleData

class PeoplesAdapter: RecyclerView.Adapter<PeoplesAdapter.ViewHolder>() {
    private var peopleItems = ArrayList<PeopleData>()

    fun setUpdatedPeople(items : ArrayList<PeopleData>) {
        this.peopleItems = items
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.people_name)
        private val gender: TextView = view.findViewById(R.id.people_gender)

        fun bind(data : PeopleData) {
            name.text = data.name
            gender.text = data.gender
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_people, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(peopleItems[position])
    }

    override fun getItemCount(): Int = peopleItems.size
}