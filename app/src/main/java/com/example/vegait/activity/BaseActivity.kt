package com.example.vegait.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vegait.InternetUtil

open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        InternetUtil(this).registerNetworkCallback()
    }
}