package com.jdebit_scan_example

import android.content.Intent
import android.os.Bundle
import com.jdebit_scan.JdebitScanActivity

import io.flutter.app.FlutterActivity
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity : FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GeneratedPluginRegistrant.registerWith(this)

        val intent = Intent()
        intent.setClass(this, JdebitScanActivity::class.java)
        startActivity(intent)
    }
}



