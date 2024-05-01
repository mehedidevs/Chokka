package com.mehedi.chokka

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mehedi.chokka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val ROLL_DURATION = 5000L
    private val ROLL_INTERVAL = 300L


    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<DiceViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnRoll.setOnClickListener {
            diceRoll()
        }
        diceNumberObserver()
    }

    private fun diceNumberObserver() {
        viewModel.pickedDice.observe(this) { dice ->
            binding.imgDice.setImageResource(dice)
        }

    }

    private fun diceRoll() {
        val countDownTimer = object : CountDownTimer(ROLL_DURATION, ROLL_INTERVAL) {
            override fun onTick(p0: Long) {
                selectedDice()
                binding.btnRoll.disabled()
            }

            override fun onFinish() {
                selectedDice()
                binding.btnRoll.enabled()


            }
        }
        countDownTimer.start()


    }

    private fun selectedDice() {
        viewModel.rollDice()
    }
}