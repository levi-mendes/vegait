package com.example.vegait.util

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, resId: Int) {
    Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
}


fun showToast(context: Context, textMessage: String) {
    Toast.makeText(context, textMessage, Toast.LENGTH_SHORT).show()
}