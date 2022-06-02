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
import ar.com.midinero.databinding.FragmentSignUpBinding
import ar.com.midinero.ui.viewmodel.SignUpViewModel
import ar.com.midinero.ui.viewpager.LogInViewPagerFragmentDirections
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val TAG: String = "${MiDineroApp.TAG}${this.javaClass.name}"

    private lateinit var binding: FragmentSignUpBinding

    private val viewModel by viewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)

        observeIsSignUp()

        onActionSended()

        onSignUpClick()
    }

    private fun observeIsSignUp() {
        viewModel.isSignUp().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.btnSignUp.hide()
                    binding.pbSignUp.show()
                }
                is Result.Success -> {
                    goToHome()
                    binding.btnSignUp.show()
                    binding.pbSignUp.hide()
                }
                is Result.Failure -> {
                    Log.e(TAG, "error: ${result.exception}")
                    FirebaseCrashlytics.getInstance().recordException(result.exception)
                    binding.btnSignUp.show()
                    binding.pbSignUp.hide()
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
        binding.confirmPassword.setOnEditorActionListener { _, actionId, _ ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                handled = true
                signIn()
            }
            handled
        }
    }

    private fun onSignUpClick() {
        binding.btnSignUp.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        val email = binding.etEmail.editText?.text.toString().trim()
        val password = binding.etPassword.editText?.text.toString().trim()
        val confirmPassword = binding.etConfirmPassword.editText?.text.toString().trim()

        if (validateFields(email, password, confirmPassword)) {
            viewModel.signUp(email, password)
        }
    }

    private fun validateFields(email: String, password: String, confirmPassword: String): Boolean {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
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
        if (password != confirmPassword) {
            Toast.makeText(
                requireContext(),
                getString(R.string.passwords_do_not_match),
                Toast.LENGTH_LONG
            )
                .show()
            return false
        }
        return true
    }
}