package com.shop.user.ui.fragment.login

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.shop.user.R
import com.shop.user.data.datasource.Resource
import com.shop.user.databinding.FragmentLoginBinding
import com.shop.user.ui.activity.ShoppingActivity
import com.shop.user.ui.common.showSnackBarLong
import com.shop.user.ui.common.showSnackBarShort
import com.shop.user.ui.dialog.ForgotPasswordDialog
import com.shop.user.ui.dialog.OnItemClickListener
import com.shop.user.ui.fragment.BaseFragment
import com.shop.user.viewmodel.login.LoginViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    /*========================================================================
      VARIABLES
    =========================================================================*/
    private lateinit var viewModel: LoginViewModel

    /*========================================================================
      OVERRIDDEN METHODS
    =========================================================================*/
    @SuppressLint("ResourceAsColor")
    override fun initView() {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        Timber.i("bindProgressButton")
        bindProgressButton(binding.login)
        binding.login.attachTextChangeAnimator()

        binding.apply {
            login.setOnClickListener {
                Timber.i("onCLick Login")
                val email = email.text.toString().trim()
                val password = password.text.toString().trim()
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.loginWithEmailAndPassword(email, password)
                } else {
                    Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            register.setOnClickListener {
                Timber.i("onCLick Register")
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
            forgotPassword.setOnClickListener {
                Timber.i("onCLick Forgot Password")
//                setupBottomSheetDialog { email ->
//                    viewModel.resetPassword(email)
//                }
                val forgotPasswordDialog = ForgotPasswordDialog(onItemClickListener = object :
                    OnItemClickListener {
                    override fun onItemClick(email: String) {
                        viewModel.resetPassword(email)
                    }
                })
                forgotPasswordDialog.show(parentFragmentManager, ForgotPasswordDialog.TAG)
            }
        }

        lifecycleScope.launch {
            viewModel.login.collect {
                when (it) {
                    // handle loading
                    is Resource.Loading -> {
                        Timber.i("Login loading...")
                        binding.login.showProgress {
                            buttonTextRes = R.string.loading
                            progressColor = R.color.white
                        }
                    }
                    // handle login success
                    is Resource.Success -> {
                        Timber.i("Login success... ${it.data}")
                        binding.login.hideProgress(R.string.submit)
                        Intent(requireActivity(), ShoppingActivity::class.java).also { intent ->
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)
                        }

                    }
                    // handle login failure
                    is Resource.Error -> {
                        Timber.i("Login error: ${it.message}")
                        binding.login.hideProgress(R.string.failed)
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }

                    else -> Unit
                }
            }
        }
        lifecycleScope.launch {
            viewModel.resetPass.collect {
                when (it) {
                    is Resource.Loading -> {
                        Timber.i("Reset password loading...")
                    }

                    is Resource.Success -> {
                        Timber.i("Reset password success: ${it.data}")
                        showSnackBarLong(getString(R.string.notify_reset_link), requireView())
                    }

                    is Resource.Error -> {
                        Timber.i("Reset password error: ${it.message}")
                        showSnackBarShort("ERROR: ${it.message}", requireView())
                    }

                    else -> Unit
                }
            }
        }
    }

    override fun observeData() {
    }
}