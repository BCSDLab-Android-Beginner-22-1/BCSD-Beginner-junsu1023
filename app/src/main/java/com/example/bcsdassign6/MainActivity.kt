package com.example.bcsdassign6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.my_recycler_view)
        val nameText: EditText = findViewById(R.id.input_name_text)
        val addButton: ImageButton = findViewById(R.id.add_to_list_button)

        val adapter = ItemAdapter()
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        addButton.setOnClickListener {
            adapter.addData(NameData(nameText.text.toString()))
            nameText.setText("")
        }

        adapter.setOnItemClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.delete))
                .setPositiveButton(getString(R.string.delete)) { _, _ ->
                    adapter.removeData(it)
                }
                .setNegativeButton(getString(R.string.dismiss)) { dialog, _ ->
                    dialog.dismiss()
                }
            builder.show()
        }

        adapter.setOnItemLongClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater

            val rootView = inflater.inflate(R.layout.dialog_edit_text, null)
            builder.setTitle(getString(R.string.edit_name)).setView(rootView)
                .setPositiveButton(getString(R.string.edit_name_confirm)) { _, _ ->
                    val changeText: EditText = rootView.findViewById(R.id.edit_name_text)
                    adapter.changeData(NameData(changeText.text.toString()), it)
                }
                .setNegativeButton(getString(R.string.edit_name_dismiss)) { dialog, _ ->
                    dialog.dismiss()
                }
            builder.show()
        }
    }
}