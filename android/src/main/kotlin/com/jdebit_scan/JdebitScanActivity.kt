package com.jdebit_scan

import android.app.Activity
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import android.Manifest.permission
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.Intent
import android.os.Bundle
import cn.bingoogolapple.qrcode.core.QRCodeView
import kotlinx.android.synthetic.main.activity_scan.*


class JdebitScanActivity : Activity(), EasyPermissions.PermissionCallbacks, QRCodeView.Delegate {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)
        zxingview.setDelegate(this)

    }

    override fun onStart() {
        super.onStart()
        requestCodeQRCodePermissions()
    }

    override fun onStop() {
        zxingview.stopCamera()
        super.onStop()

    }

    override fun onDestroy() {
        zxingview.onDestroy()
        super.onDestroy()
    }


    override fun onScanQRCodeSuccess(result: String) {
        if (result != null) {
            val intent = Intent()
            intent.putExtra("SCAN_RESULT", result)
            setResult(RESULT_OK, intent)
        } else {
            val intent = Intent()
            intent.putExtra("ERROR_CODE", "读取失败")
            setResult(RESULT_CANCELED, intent)
        }
        finish()
    }

    override fun onCameraAmbientBrightnessChanged(isDark: Boolean) {
    }

    override fun onScanQRCodeOpenCameraError() {
    }


    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
    }


    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }


    @AfterPermissionGranted(1)
    private fun requestCodeQRCodePermissions() {
        val perms = arrayOf(permission.CAMERA, READ_EXTERNAL_STORAGE)
        if (!EasyPermissions.hasPermissions(this, *perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", 1, *perms)
        } else {
            zxingview.startCamera()
            zxingview.scanBoxView.isOnlyDecodeScanBoxArea = true
            zxingview.startSpotAndShowRect()
        }
    }


}