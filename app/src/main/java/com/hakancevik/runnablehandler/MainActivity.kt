package com.hakancevik.runnablehandler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.hakancevik.runnablehandler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var number = 0
    var runnable: Runnable = Runnable { }
    var handler: Handler = Handler(Looper.getMainLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.stopButton.isEnabled = false
        binding.resetButton.isEnabled = false


    }

    fun start(view: View) {

        runnable = object : Runnable {
            override fun run() {

                number += 1
                binding.timeText.text = "Time: ${number}"
                handler.postDelayed(this, 1000)

            }

        }
        handler.post(runnable)

        binding.startButton.isEnabled = false
        binding.stopButton.isEnabled = true
        binding.resetButton.isEnabled = true


    }

    fun stop(view: View) {

        handler.removeCallbacks(runnable);

        binding.startButton.isEnabled = true
        binding.stopButton.isEnabled = false
        binding.resetButton.isEnabled = true


    }

    fun reset(view: View) {
        handler.removeCallbacks(runnable)
        number = 0
        binding.timeText.text = "Time: $number"


        binding.startButton.isEnabled = true
        binding.stopButton.isEnabled = false
        binding.resetButton.isEnabled = false


    }


}