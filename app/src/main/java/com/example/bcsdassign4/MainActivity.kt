package com.example.bcsdassign4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var getResultText: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var count = 0
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

        val getResultText = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            count = it.data!!.getIntExtra("count", 0)
            numberText.text = count.toString()
        }

        randomButton.setOnClickListener {
            val intent = Intent(this, ChangeActivity::class.java)
            intent.putExtra("count", count)
            getResultText.launch(intent)
        }
    }
}