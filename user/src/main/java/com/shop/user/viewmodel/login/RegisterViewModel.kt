package com.shop.user.viewmodel.login

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.shop.user.common.Constants.USER_COLLECTION
import com.shop.user.common.validateEmail
import com.shop.user.common.validatePassword
import com.shop.user.data.model.RegisterFieldState
import com.shop.user.data.model.RegisterValidation
import com.shop.user.data.model.User
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import timber.log.Timber

class RegisterViewModel : ViewModel() {
    /*========================================================================
      VARIABLES
    =========================================================================*/
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _register =
        MutableStateFlow<com.shop.user.data.datasource.Resource<User>>(com.shop.user.data.datasource.Resource.Unspecified())
    val register: Flow<com.shop.user.data.datasource.Resource<User>> = _register

    private val _validation = Channel<RegisterFieldState>()
    val validation = _validation.receiveAsFlow()

    /*========================================================================
      FUNCTIONS
    =========================================================================*/
    fun createAccountWitEmailAndPassword(user: User, password: String) {
        Timber.i("Create Account With Email : $user And Password : $password")
        if (checkValidation(user, password)) {
            runBlocking {
                _register.emit(com.shop.user.data.datasource.Resource.Loading())
            }
            firebaseAuth.createUserWithEmailAndPassword(user.email, password)
                .addOnSuccessListener {
                    it.user?.let {
                        saveUserInfo(it.uid, user)
                    }
                }
                .addOnFailureListener {

                }
        } else {
            val registerFieldState = RegisterFieldState(
                validateEmail(user.email), validatePassword(password)
            )
            runBlocking {
                _validation.send(registerFieldState)
            }
        }
    }

    private fun saveUserInfo(userId: String, user: User) {
        Timber.i("Save User Info")
        db.collection(USER_COLLECTION)
            .document()
            .set(user)
            .addOnSuccessListener {
                _register.value = com.shop.user.data.datasource.Resource.Success(user)
            }
            .addOnFailureListener {
                _register.value =
                    com.shop.user.data.datasource.Resource.Error(it.message.toString())
            }
    }

    private fun checkValidation(user: User, password: String): Boolean {
        Timber.i("Check Validation: $user And $password")
        val emailValidation = validateEmail(user.email)
        val passwordValidation = validatePassword(password)
        val shouldRegister = emailValidation is RegisterValidation.Success
                && passwordValidation is RegisterValidation.Success
        return shouldRegister
    }
}