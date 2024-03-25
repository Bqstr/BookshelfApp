package com.example.onelab_homework

import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.example.onelab_homework.databinding.ActivityMainBinding
import com.example.onelab_homework.reciver.MyReceiver

class MainActivity : AppCompatActivity() {
    lateinit var binding :ActivityMainBinding
    private lateinit var navController : NavController
    lateinit var receiver: MyReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        App.init(applicationContext)
        receiver = MyReceiver()









        setContentView(binding.root)
    }
    override fun onResume() {
        super.onResume()
        val filter = IntentFilter("my_custom_action")
        registerReceiver(receiver, filter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }
}