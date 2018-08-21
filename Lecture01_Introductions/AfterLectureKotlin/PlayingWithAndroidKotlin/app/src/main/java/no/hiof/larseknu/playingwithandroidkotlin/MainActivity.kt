package no.hiof.larseknu.playingwithandroidkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {
    private var LOG_TAG = "PlayingWithAndroid";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart() ran")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume() ran")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "onPause() ran")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "onStop() ran")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(LOG_TAG, "onRestart() ran")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy() ran")
    }

    fun navigateToOtherActivity(view: View) {
        var intent = Intent(this, OtherActivity::class.java)
        startActivity(intent)
    }
}
