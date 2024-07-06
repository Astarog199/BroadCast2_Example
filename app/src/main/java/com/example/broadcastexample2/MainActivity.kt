package com.example.broadcastexample2

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

val receiver = BroadcastReceiver2()

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter("Broadcast_2")
        textView = findViewById(R.id.textView2)
        registerReceiver(receiver, filter, RECEIVER_EXPORTED)

        sendBroadcast(Intent("Broadcast_1"))
    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    override fun onResume() {
        super.onResume()
        updateCounters()
    }

    companion object {
        lateinit var textView: TextView

        fun updateCounters() {
            if (::textView.isInitialized) {
                textView.text = receiver.counter.toString()
            }
        }
    }
}