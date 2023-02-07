package com.example.taskmanager.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.taskmanager.R

fun ImageView.loadImage(image: String?){
    Glide.with(this).load(image).placeholder(R.drawable.ic_profile).into(this)
}

fun Context.isOnline(): Boolean {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivityManager != null) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true
            }
        }
    }
    return false
}