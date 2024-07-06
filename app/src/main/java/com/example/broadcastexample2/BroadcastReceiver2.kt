package com.example.broadcastexample2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BroadcastReceiver2: BroadcastReceiver() {
    private val scope = CoroutineScope(Dispatchers.IO)
    var counter = 0

    override fun onReceive(context: Context, intent: Intent) {

        val result = goAsync()
        val thread: Thread = object : Thread() {
            override fun run() {
                if (intent.action == "Broadcast_2") {
                    counter++

                    val handler = Handler(context.mainLooper)
                    handler.post{
                        MainActivity.updateCounters()
                    }

                }
                sleep(3000)
                context.sendBroadcast(
                    Intent("Broadcast_1"),
                    "com.example.broadcastexample.CALL_IN_THE_MIST")
            }
        }
        result.finish()
        thread.start()
    }
}