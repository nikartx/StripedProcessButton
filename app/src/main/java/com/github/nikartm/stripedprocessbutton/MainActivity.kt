package com.github.nikartm.stripedprocessbutton

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.nikartm.stripedprocessbutton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindView()
    }

    private fun bindView() = with(binding) {
        initStartStripedButton()
        btnStart.setOnClickListener {
            btnStart.start()
            btn2.start()
            btn3.start()
            btn4.start()
        }
        btnStop.setOnClickListener {
            btnStart.stop()
            btn2.stop()
            btn3.stop()
            btn4.stop()
        }
    }

    private fun initStartStripedButton() = with(binding.btnStart) {
        text = "Start process"
        setCornerRadius(50f)
        setStripeAlpha(0.7f)
        setStripeRevert(true)
        setStripeGradient(false)
        setTilt(15f)
    }
}
