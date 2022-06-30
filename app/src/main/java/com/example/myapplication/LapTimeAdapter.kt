package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.TimeUnit

class LapTimeAdapter: RecyclerView.Adapter<LapTimeAdapter.ViewHolder>() {
    private val lapTimeList = mutableListOf<LapTimeData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lap_time_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val time = lapTimeList[position].time
            val curMinutes = TimeUnit.MILLISECONDS.toMinutes(time)
            val curSeconds =
                TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(curMinutes)
            val curMilliSeconds =
                time - TimeUnit.SECONDS.toMillis(curSeconds) - TimeUnit.MINUTES.toMillis(curMinutes)
            lapTimeTextView.text = String.format("%02d : %02d : %02d", curMinutes, curSeconds, curMilliSeconds / 10)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lapTimeTextView: TextView = itemView.findViewById(R.id.lap_time_text_view)
    }

    override fun getItemCount(): Int = lapTimeList.size

    fun setList(list: MutableList<LapTimeData>) {
        for(i in list) lapTimeList.add(i)
    }

    fun addLapTime(lapTime: LapTimeData) {
        lapTimeList.add(lapTime)
        notifyDataSetChanged()
    }
}