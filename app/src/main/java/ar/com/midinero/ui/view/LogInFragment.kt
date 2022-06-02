package ar.com.midinero.ui.view

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ar.com.midinero.MiDineroApp
import ar.com.midinero.R
import ar.com.midinero.core.Result
import ar.com.midinero.core.hide
import ar.com.midinero.core.show
import ar.com.midinero.databinding.FragmentLogInBinding
import ar.com.midinero.ui.viewmodel.LogInViewModel
import ar.com.midinero.ui.viewpager.LogInViewPagerFragmentDirections
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern


@AndroidEntryPoint
class LogInFragment : Fragment(R.layout.fragment_log_in) {

    private val TAG: String = "${MiDineroApp.TAG}${this.javaClass.name}"

    private lateinit var binding: FragmentLogInBinding

    private val viewModel by viewModels<LogInViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLogInBinding.bind(view)

        observeIsUserAuth()

        onActionSended()

        onLogInClick()
    }

    private fun observeIsUserAuth() {
        viewModel.isUserAuth().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.btnLogIn.hide()
                    binding.pbLogIn.show()
                }
                is Result.Success -> {
                    goToHome()
                    binding.btnLogIn.show()
                    binding.pbLogIn.hide()
                }
                is Result.Failure -> {
                    when (result.exception.cause) {
                        is FirebaseAuthInvalidUserException -> {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.invalid_email),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                        is FirebaseAuthInvalidCredentialsException -> {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.invalid_password),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                        else -> {
                            Log.e(TAG, "error: ${result.exception}")
                            FirebaseCrashlytics.getInstance().recordException(result.exception)
                        }
                    }
                    binding.btnLogIn.show()
                    binding.pbLogIn.hide()
                }
            }
        }
    }

    private fun goToHome() {
        findNavController().navigate(
            LogInViewPagerFragmentDirections.actionLogInViewPagerFragmentToHomeFragment()
        )
    }

    fun onActionSended() {
        binding.password.setOnEditorActionListener { _, actionId, _ ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                handled = true
                logIn()
            }
            handled
        }
    }

    private fun onLogInClick() {
        binding.btnLogIn.setOnClickListener {
            logIn()
        }
    }

    private fun logIn() {
        val email = binding.etEmail.editText?.text.toString().trim()
        val password = binding.etPassword.editText?.text.toString().trim()

        if (validateFields(email, password)) {
            viewModel.logIn(email, password)
        }
    }

    private fun validateFields(email: String, password: String): Boolean {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.complete_fields), Toast.LENGTH_LONG)
                .show()
            return false
        }
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        if (!pattern.matcher(email).matches()) {
            Toast.makeText(requireContext(), getString(R.string.only_email), Toast.LENGTH_LONG)
                .show()
            return false
        }
        return true
    }
}