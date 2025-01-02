package com.example.fixed_device.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.fixed_device.databinding.ActivityReceiveMerchandiseBinding

class ReceiveMerchandiseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReceiveMerchandiseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityReceiveMerchandiseBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}