package com.example.fixed_device.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun showSnackBar(view : View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}

@RequiresApi(Build.VERSION_CODES.M)
fun isNetWorkConnected(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val networkCapabilities =
        connectivityManager.getNetworkCapabilities(network) ?: return false
    return when {
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
        else -> false
    }
}

fun hideKeyBoard(context: Context, view: View) {
    val imm = context
        .getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(view.windowToken, 0)
}
