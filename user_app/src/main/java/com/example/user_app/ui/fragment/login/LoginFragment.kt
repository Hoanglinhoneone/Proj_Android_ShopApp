package com.example.user_app.ui.fragment.login

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.user_app.R
import com.example.user_app.data.datasource.Resource
import com.example.user_app.databinding.FragmentLoginBinding
import com.example.user_app.dialog.setupBottomSheetDialog
import com.example.user_app.ui.activity.ShoppingActivity
import com.example.user_app.ui.common.showSnackBarLong
import com.example.user_app.ui.common.showSnackBarShort
import com.example.user_app.ui.fragment.BaseFragment
import com.example.user_app.viewmodel.LoginViewModel
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : BaseFragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()
    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }

    @SuppressLint("ResourceAsColor")
    override fun setUpView(view: View) {
        binding = FragmentLoginBinding.bind(view)

        // bind button login with progressbutton
        bindProgressButton(binding.btnLogin)
        binding.btnLogin.attachTextChangeAnimator()

        binding.apply {
            btnLogin.setOnClickListener {

                val email = edtEmail.text.toString().trim()
                val password = edtPassword.text.toString().trim()
                viewModel.loginWithEmailAndPassword(email, password)
                // if success
//                binding.btnLogin.hideProgress(R.string.submit)
            }
            tvRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
            tvForgotPass.setOnClickListener {
                setupBottomSheetDialog { email ->
                    viewModel.resetPassword(email)
                }
            }
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.login.collect {
                    when (it) {
                        // handle loading
                        is Resource.Loading -> {
                            Timber.d("Login loading...")
                            binding.btnLogin.showProgress {
                                buttonTextRes = R.string.loading
                                progressColor = R.color.white
                            }
                        }
                        // handle login success
                        is Resource.Success -> {
                            Timber.d("Login success... ${it.data}")
                            binding.btnLogin.hideProgress(R.string.submit)
                            Intent(requireActivity(), ShoppingActivity::class.java).also { intent ->
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                startActivity(intent)
                            }

                        }
                        // handle login failure
                        is Resource.Error -> {
                            Timber.d("Login error: ${it.message}")
                            binding.btnLogin.hideProgress(R.string.failed)
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }

                        else -> Unit
                    }
                }
            }
        }
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.resetPass.collect {
                    when (it) {
                        is Resource.Loading -> {
                            Timber.d("Reset password loading...")
                        }
                        is Resource.Success -> {
                            Timber.d("Reset password success: ${it.data}")
                            showSnackBarLong(getString(R.string.notify_reset_link), requireView())
                        }
                        is Resource.Error -> {
                            Timber.d("Reset password error: ${it.message}")
                            showSnackBarShort("ERROR: ${it.message}", requireView())
                        }
                        else -> Unit
                    }
                }
            }
        }
    }
}