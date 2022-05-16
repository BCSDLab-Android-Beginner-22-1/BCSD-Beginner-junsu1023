package com.example.bcsdassign6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter: RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {
    private val dataList = mutableListOf<NameData>()
    lateinit var onItemClickListener: OnItemClickListener
    lateinit var onItemLongClickListener: OnItemLongClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemAdapter.MyViewHolder, position: Int) {
        with(holder) {
            nameTextView.text = dataList[position].name
            nameTextView.setOnClickListener {
                onItemClickListener.onItemClick(position)
            }
            nameTextView.setOnLongClickListener {
                onItemLongClickListener.onItemLongClick(position)
                return@setOnLongClickListener true
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    class MyViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.name_text_view)
    }

    fun addData(data: NameData) {
        dataList.add(data)
        notifyDataSetChanged()
    }

    fun changeData(data: NameData, position: Int) {
        dataList[position] = data
        notifyDataSetChanged()
    }

    fun removeData(position: Int) {
        dataList.removeAt(position)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(position: Int)
    }

    inline fun setOnItemClickListener(crossinline onItemClick: (Int) -> Unit) {
        onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                onItemClick(position)
            }
        }
    }

    inline fun setOnItemLongClickListener(crossinline onItemLongClick: (Int) -> Unit) {
        onItemLongClickListener = object : OnItemLongClickListener {
            override fun onItemLongClick(position: Int) {
                onItemLongClick(position)
            }
        }
    }
}