package com.example.sdk.core.network

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

/**
Helper, containing application work mode(offline/online) and allow you to check network connection status
 */

interface NetworkHelper {
    var isOfflineModeEnabled: Boolean
    fun isConnectedToNetwork(setAsOfflineMode: Boolean = false): Boolean
}

class NetworkHelperImpl @Inject constructor(private val context: Context) : NetworkHelper {

    override fun isConnectedToNetwork(setAsOfflineMode: Boolean) = isInternetAvailable(context, setAsOfflineMode)

    override var isOfflineModeEnabled: Boolean = false

    //As Default we just check is internet available. With `setAsOnlineMode` we save result as App working mode
    private fun isInternetAvailable(context: Context, setAsOfflineMode: Boolean = false): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        val state = activeNetwork != null && activeNetwork.isConnected
        if (setAsOfflineMode) {
            isOfflineModeEnabled = !state
        }
        return state
    }
}