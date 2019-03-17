package sayan.banerjee.newsapp.common

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import sayan.banerjee.newsapp.common.network.listener.INetworkListener
import sayan.banerjee.newsapp.common.network.receiver.NetworkReceiver
import sayan.banerjee.newsapp.common.network.util.NetworkConstants
import sayan.banerjee.newsapp.common.network.util.NetworkUtil
import sayan.banerjee.newsapp.common.network.util.displayToast

open class BaseActivity : AppCompatActivity(), INetworkListener {
    private var mNetworkReceiver: NetworkReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NetworkUtil.setConnectivityListener(this)
        registerNetworkReceiver()
    }

    private fun registerNetworkReceiver() {
        mNetworkReceiver = NetworkReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        this.registerReceiver(mNetworkReceiver, intentFilter)
    }

    override fun onConnectivityChange(connectionStatus: String) {
        when (connectionStatus) {
            NetworkConstants.OPTIMAL_NETWORK -> {
                mIsNetworkAvailable = true
                mIsConnectivityPoor = false
                this.displayToast("Optimal Network")
            }
            NetworkConstants.POOR_NETWORK -> {
                mIsNetworkAvailable = true
                mIsConnectivityPoor = true
                this.displayToast("Poor Network")
            }
            NetworkConstants.TYPE_WIFI -> {
                mIsNetworkAvailable = true
                mIsConnectivityPoor = false
                this.displayToast("Wifi Network")
            }
            NetworkConstants.TYPE_NOT_CONNECTED -> {
                mIsNetworkAvailable = false
                mIsConnectivityPoor = false
                this.displayToast("No Network")
            }
        }

        if(mIsNetworkAvailable) initOnlineFlow() else initOfflineFlow()
    }

    open fun initOnlineFlow() {
        //Needs to be implemented in respective activity/fragments
    }

    open fun initOfflineFlow() {
        //Needs to be implemented in respective activity/fragments
    }

    companion object {
        val TAG: String = BaseActivity::class.java.simpleName
        var mIsNetworkAvailable: Boolean = false
        var mIsConnectivityPoor: Boolean = false
    }

}