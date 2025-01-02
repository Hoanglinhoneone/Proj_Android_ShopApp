package com.example.user_app.ui.common


import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

fun toastShort(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun toastLong(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun showSnackBarShort(message: String, view: View) {
    val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
    snackBar.show()
    Timber.d("showSnackBarShort: $message")
}

fun showSnackBarLong(message: String, view: View) {
    val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
    snackBar.show()
    Timber.d("showSnackBarLong: $message")
}