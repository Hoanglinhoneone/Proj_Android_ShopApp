package com.shop.user.ui.common


import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

fun toastShort(message: String, context: Context) {
    Timber.i("toastShort: $message")
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun toastLong(message: String, context: Context) {
    Timber.i("toastLong: $message")
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun showSnackBarShort(message: String, view: View) {
    Timber.i("showSnackBarShort: $message")
    val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
    snackBar.show()
}

fun showSnackBarLong(message: String, view: View) {
    Timber.i("showSnackBarLong: $message")
    val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
    snackBar.show()
}