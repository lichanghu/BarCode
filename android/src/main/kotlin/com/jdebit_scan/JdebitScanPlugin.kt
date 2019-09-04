package com.jdebit_scan

import android.app.Activity
import android.content.Intent
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry
import io.flutter.plugin.common.PluginRegistry.Registrar

class JdebitScanPlugin(val activity: Activity) : MethodCallHandler, PluginRegistry.ActivityResultListener {


    var result: Result? = null

    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "jdebit_scan")
            if (registrar.activity() != null) {
                val plugin = JdebitScanPlugin(registrar.activity())
                channel.setMethodCallHandler(plugin)
                registrar.addActivityResultListener(plugin)
            }

        }
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        if (call.method == "scan") {
            this.result = result
            showJdebitScanView()
        } else {
            result.notImplemented()
        }

    }


    private fun showJdebitScanView() {
        val intent = Intent(activity, JdebitScanActivity::class.java)
        activity.startActivityForResult(intent, 100)
    }

    override fun onActivityResult(code: Int, resultCode: Int, data: Intent?): Boolean {
        if (code == 100) {
            if (resultCode == Activity.RESULT_OK) {
                val barcode = data?.getStringExtra("SCAN_RESULT")
                barcode?.let {
                    this.result?.success(barcode)
                }
            } else {
                val errorCode = data?.getStringExtra("ERROR_CODE")
                this.result?.error(errorCode, null, null)
            }
            return true
        }
        return false
    }
}

