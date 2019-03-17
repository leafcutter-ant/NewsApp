package sayan.banerjee.newsapp.common.network.listener

interface INetworkListener {
    fun onConnectivityChange(connectionStatus: String)
}