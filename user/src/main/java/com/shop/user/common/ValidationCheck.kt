package com.shop.user.common

import android.util.Patterns
import com.shop.user.data.model.RegisterValidation
import timber.log.Timber

fun validateEmail(email: String) : RegisterValidation {
    Timber.i("validateEmail: $email")
    if(email.isEmpty()) {
        return RegisterValidation.Failed("Email cannot be empty")
    }
    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        return RegisterValidation.Failed("Wrong email format")
    }
    return RegisterValidation.Success
}

fun validatePassword(password: String) : RegisterValidation {
    Timber.i("validatePassword: $password")
    if(password.isEmpty()) {
        return RegisterValidation.Failed("Password cannot be empty")
    }
    if (password.length < 6) {
        return RegisterValidation.Failed("Password should contains 6 char")
    }

    return RegisterValidation.Success
}