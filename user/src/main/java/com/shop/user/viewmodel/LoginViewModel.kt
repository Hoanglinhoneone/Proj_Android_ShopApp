package com.shop.user.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _login = MutableSharedFlow<com.shop.user.data.datasource.Resource<FirebaseUser>>()
    val login = _login.asSharedFlow()

    private val _resetPass = MutableSharedFlow<com.shop.user.data.datasource.Resource<String>>()
    val resetPass = _resetPass.asSharedFlow()

    fun loginWithEmailAndPassword(email: String, password: String) {
        Timber.i("Login with email and password")
        viewModelScope.launch {
            _login.emit(com.shop.user.data.datasource.Resource.Loading())
        }

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                viewModelScope.launch {
                    it.user?.let {
                        Timber.i("Login success with email : ${it.email}")
                        _login.emit(com.shop.user.data.datasource.Resource.Success(it))
                    }
                }
            }
            .addOnFailureListener {
                viewModelScope.launch {
                    Timber.i("Login failure: ${it.message}")
                    _login.emit(com.shop.user.data.datasource.Resource.Error(it.message.toString()))
                }
            }
    }

    fun resetPassword(email: String) {
        Timber.i("Reset password email: $email")
        viewModelScope.launch {
            _resetPass.emit(com.shop.user.data.datasource.Resource.Loading())
        }
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                Timber.i("Reset password success")
                viewModelScope.launch {
                    _resetPass.emit(com.shop.user.data.datasource.Resource.Success(email))
                }
            }
            .addOnFailureListener {
                Timber.i("Reset password failure: ${it.message}")
                viewModelScope.launch {
                    _resetPass.emit(com.shop.user.data.datasource.Resource.Error(it.message.toString()))
                }
            }
    }

}