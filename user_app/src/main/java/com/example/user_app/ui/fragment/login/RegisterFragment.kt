package com.example.user_app.ui.fragment.login

import android.graphics.Color
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.user_app.R
import com.example.user_app.common.RegisterValidation
import com.example.user_app.data.datasource.Resource
import com.example.user_app.data.model.User
import com.example.user_app.databinding.FragmentRegisterBinding
import com.example.user_app.ui.fragment.BaseFragment
import com.example.user_app.viewmodel.RegisterViewModel
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@AndroidEntryPoint
class RegisterFragment : BaseFragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()

    override fun getLayoutId(): Int {
        return R.layout.fragment_register
    }

    override fun setUpView(view: View) {
        Timber.d("Setup View")
        binding = FragmentRegisterBinding.bind(view)
        bindProgressButton(binding.btnRegister)
        binding.apply {
            btnRegister.setOnClickListener {
                btnRegister.attachTextChangeAnimator()
                // Show progress with "Loading" text
                val user = User(
                    edtFirstName.text.toString().trim(),
                    edtLastName.text.toString().trim(),
                    edtEmail.text.toString().trim(),
                )
                val password = edtPassword.text.toString().trim()
                viewModel.createAccountWitEmailAndPassword(user, password)
                Timber.d("Input User: $user + \n Password: $password")


            }
            tvLogin.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }

        //  handle result register
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.register.collect { event ->
                    when (event) {
                        is Resource.Loading -> {
                            // Xử lý trạng thái Loading
                            Timber.d("Loading...")
                            binding.btnRegister.showProgress {
                                buttonTextRes = R.string.loading
                                progressColor = Color.WHITE
                            }
                        }

                        is Resource.Success -> {
                            // Xử lý trạng thái Success
                            Timber.d("Success :" + event.data.toString())
                            binding.btnRegister.hideProgress(R.string.submit)
                        }

                        is Resource.Error -> {
                            // Xử lý trạng thái Error
                            Timber.d("Error :" + event.message.toString())
                            binding.btnRegister.hideProgress(R.string.failed)
                        }

                        else -> Unit
                    }
                }
            }
        }

        // validate email && password
//        lifecycleScope.launch {
//            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.validation.collect { validation ->
//                    if (validation.email is RegisterValidation.Failed) {
//                        binding.edtEmail.apply {
//                            requestFocus()
//                            error = validation.email.message
//                            Timber.d("Email error: ${validation.email.message}")
//                        }
//                    }
//                    if (validation.password is RegisterValidation.Failed) {
//                        binding.edtPassword.apply {
//                            requestFocus()
//                            error = validation.password.message
//                            Timber.d("Password error: ${validation.password.message}")
//                        }
//                    }
//                }
//            }
//        }
        lifecycleScope.launchWhenStarted {
            viewModel.validation.collect { validation ->
                if (validation.email is RegisterValidation.Failed){
                    withContext(Dispatchers.Main){
                        binding.edtEmail.apply {
                            requestFocus()
                            error = validation.email.message
                        }
                    }
                }

                if (validation.password is RegisterValidation.Failed){
                    withContext(Dispatchers.Main){
                        binding.edtPassword.apply {
                            requestFocus()
                            error = validation.password.message
                        }
                    }
                }
            }
        }
    }
}