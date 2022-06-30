package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private var checkTimerRunning = false
    private var time = 0L
    private var timer: Job? = null

    private lateinit var timeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeTextView = findViewById(R.id.time_text_view)
        val startPauseButton: Button = findViewById(R.id.start_pause_button)
        val stopButton: Button = findViewById(R.id.stop_button)
        val lapButton: Button = findViewById(R.id.lap_button)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        startPauseButton.setOnClickListener {
            checkTimerRunning = if(checkTimerRunning) {
                timer!!.cancel()
                startPauseButton.text = getString(R.string.Start)
                false
            }
            else {
                startTimer()
                startPauseButton.text = getString(R.string.Pause)
                true
            }
        }

        stopButton.setOnClickListener {
            timer!!.cancel()
            startPauseButton.text = getString(R.string.Start)
            time = 0L
            timeTextView.text = getString(R.string.initial_time)
            checkTimerRunning = false
        }

        val lapTimeAdapter = LapTimeAdapter()
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = lapTimeAdapter

        lapButton.setOnClickListener {
            lapTimeAdapter.addLapTime(LapTimeData(time))
            lapTimeAdapter.notifyItemInserted(0)
        }
    }

    private fun startTimer() {
        checkTimerRunning = true
        var delayTime = 10L
        var prevTimeMilliSeconds: Long
        var curTimeMilliSeconds: Long
        var timeError: Long

        timer = CoroutineScope(Dispatchers.Main).launch {
            prevTimeMilliSeconds = System.currentTimeMillis()
            while(true) {
                delay(delayTime)
                curTimeMilliSeconds = System.currentTimeMillis()
                timeError = curTimeMilliSeconds - 10L - prevTimeMilliSeconds
                delayTime -= timeError
                prevTimeMilliSeconds = curTimeMilliSeconds

                val curMinutes = TimeUnit.MILLISECONDS.toMinutes(time)
                val curSeconds = TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(curMinutes)
                val curMilliSeconds = time - TimeUnit.SECONDS.toMillis(curSeconds) - TimeUnit.MINUTES.toMillis(curMinutes)

                timeTextView.text = String.format("%02d : %02d : %02d", curMinutes, curSeconds, curMilliSeconds / 10)
                time += 10
            }
        }
    }
}