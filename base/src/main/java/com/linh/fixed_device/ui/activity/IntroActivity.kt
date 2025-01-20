package com.linh.fixed_device.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.linh.fixed_device.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        setUpListener()

    }

    private fun setUpListener() {
        binding.btnBuyNow.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        binding.btnReceiveMerchandise.setOnClickListener {
            val intent = Intent(this, ReceiveMerchandiseActivity::class.java)
            startActivity(intent)
        }
    }
}