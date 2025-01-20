package com.shop.user.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shop.user.R

class ForgotPasswordDialog(private val onItemClickListener: OnItemClickListener) :
    BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dialog_reset_password, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnCancel = view.findViewById<Button>(R.id.btn_cancel)
        val btnSend = view.findViewById<Button>(R.id.btn_send)

        btnCancel.setOnClickListener {
            dismiss()
        }
        btnSend.setOnClickListener {
            // send email reset password
            val email = view.findViewById<EditText>(R.id.edt_email).text.toString().trim()
            onSendClick(email)
        }
    }

    private fun onSendClick(email: String) {
        onItemClickListener.onItemClick(email)
    }

    companion object {
        const val TAG = "ForgotPasswordDialog"
    }
}

interface OnItemClickListener {
    fun onItemClick(email: String)
}
