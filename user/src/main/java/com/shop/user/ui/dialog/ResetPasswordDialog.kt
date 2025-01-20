//package com.shop.user.ui.dialog
//
//import android.annotation.SuppressLint
//import android.widget.Button
//import android.widget.EditText
//import androidx.fragment.app.Fragment
//import com.google.android.material.bottomsheet.BottomSheetBehavior
//import com.google.android.material.bottomsheet.BottomSheetDialog
//import com.shop.user.R
//import com.shop.user.ui.common.toastShort
//import timber.log.Timber
//
//@SuppressLint("InflateParams")
//fun Fragment.setupBottomSheetDialog(
//    onSendClick: (String) -> Unit
//) {
//    // create view
//    val dialog = BottomSheetDialog(requireContext(), R.style.DialogStyle)
//    val view = layoutInflater.inflate(R.layout.dialog_reset_password, null)
//
//    // setting dialog
//    dialog.apply {
//        setContentView(view)
//        behavior.state = BottomSheetBehavior.STATE_EXPANDED
//        show()
//        Timber.i("setupBottomSheetDialog: show")
//    }
//
//    // bind view
//    val edtEmail = view.findViewById<EditText>(R.id.edt_email)
//    val btnCancel = view.findViewById<Button>(R.id.btn_cancel)
//    val btnSend = view.findViewById<Button>(R.id.btn_send)
//
//    //listener
//    btnSend.setOnClickListener {
//        Timber.i("onCLick send Email Reset Password")
//        val email = edtEmail.text.toString().trim()
//        onSendClick(email)
//        toastShort("Send Email Reset Password", requireContext())
//
//    }
//    btnCancel.setOnClickListener {
//        Timber.i("onCLick Cancel Email Reset Password")
//        dialog.dismiss()
//    }
//}