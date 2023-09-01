package com.example.vegait

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network

class InternetUtil(val context: Context) {

    // Network Check
    fun registerNetworkCallback() {
        try {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            //val builder = NetworkRequest.Builder()
            connectivityManager.registerDefaultNetworkCallback(object : NetworkCallback() {
                override fun onAvailable(network: Network) {
                    isNetworkConnected = true // Global Static Variable
                }

                override fun onLost(network: Network) {
                    isNetworkConnected = false // Global Static Variable
                }
            })
            isNetworkConnected = false

        } catch (e: Exception) {
            isNetworkConnected = false
        }
    }

    companion object {

        var isNetworkConnected = false
    }
}