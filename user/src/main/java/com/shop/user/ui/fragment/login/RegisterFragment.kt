package com.shop.user.ui.fragment.login

import android.graphics.Color
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.shop.user.R
import com.shop.user.data.model.RegisterValidation
import com.shop.user.data.model.User
import com.shop.user.databinding.FragmentRegisterBinding
import com.shop.user.ui.fragment.BaseFragment
import com.shop.user.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    /*========================================================================
        VARIABLES
    =========================================================================*/
    private val viewModel by viewModels<RegisterViewModel>()

    /*========================================================================
        OVERRIDDEN METHODS
    =========================================================================*/
    override fun initView() {
        bindProgressButton(binding.register)
        binding.apply {
            register.setOnClickListener {
                register.attachTextChangeAnimator()
                val user = User(
                    firstName.text.toString().trim(),
                    lastName.text.toString().trim(),
                    email.text.toString().trim(),
                )
                val password = password.text.toString().trim()
                viewModel.createAccountWitEmailAndPassword(user, password)
                Timber.d("Input User: $user + \n Password: $password")


            }
            loginNow.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }
        //  handle result register
        lifecycleScope.launch {
            viewModel.register.collect { event ->
                when (event) {
                    is com.shop.user.data.datasource.Resource.Loading -> {
                        // Xử lý Loading
                        Timber.i("Loading...")
                        binding.register.showProgress {
                            buttonTextRes = R.string.loading
                            progressColor = Color.WHITE
                        }
                    }

                    is com.shop.user.data.datasource.Resource.Success -> {
                        // Xử lý Success
                        Timber.i("Success :" + event.data.toString())
                        binding.register.hideProgress(R.string.submit)
                    }

                    is com.shop.user.data.datasource.Resource.Error -> {
                        // Xử lý Error
                        Timber.i("Error :" + event.message.toString())
                        binding.register.hideProgress(R.string.failed)
                    }

                    else -> Unit
                }
            }
        }

        // validate email && password
        lifecycleScope.launch {
            viewModel.validation.collect { validation ->
                if (validation.email is RegisterValidation.Failed) {
                    binding.email.apply {
                        requestFocus()
                        error = validation.email.message
                        Timber.i("Email error: ${validation.email.message}")
                    }
                }
                if (validation.password is RegisterValidation.Failed) {
                    binding.password.apply {
                        requestFocus()
                        error = validation.password.message
                        Timber.i("Password error: ${validation.password.message}")
                    }
                }
            }
        }
    }

    override fun observeData() {
//        TODO("Not yet implemented")
    }

}