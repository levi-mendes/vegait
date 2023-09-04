package com.example.vegait.util

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.vegait.R

fun showAlert(context: Context, message: String) {
    AlertDialog.Builder(context)
        .setTitle(R.string.text_alert_atention)
        .setIcon(R.mipmap.ic_launcher_round)
        .setMessage(message)
        .setPositiveButton(R.string.text_bt_ok_alert) { dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        .show()
}

fun showAlert(context: Context, resId: Int) {
    AlertDialog.Builder(context)
        .setTitle(R.string.text_alert_atention)
        .setIcon(R.mipmap.ic_launcher_round)
        .setMessage(resId)
        .setPositiveButton(R.string.text_bt_ok_alert) { dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        .show()
}