package com.shop.base.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.shop.base.databinding.ActivityReceiveMerchandiseBinding

class ReceiveMerchandiseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReceiveMerchandiseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityReceiveMerchandiseBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}