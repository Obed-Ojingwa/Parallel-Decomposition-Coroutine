package com.example.asyncawaitparalleldecomposition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Main).launch {
            Log.i("MyTag", "calculation started...")
            val stock1 = async(IO) {
                getStock1()
            }
            val stock2 = async(IO) {
                getStock2()
            }
            val total = stock1.await() + stock2.await()
            Toast.makeText(this@MainActivity, "total is $total", Toast.LENGTH_LONG).show()
        }

    }

    private suspend fun getStock1():Int{
        delay(4000)
        Log.i("MyTag", "stock1 returned")
        return 30009
    }

    private suspend fun getStock2():Int{
        delay(200)
        Log.i("MyTag", "stock2 returned")
        return 90009
    }

}