package ar.com.midinero.ui.view

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ar.com.midinero.MiDineroApp
import ar.com.midinero.R
import ar.com.midinero.core.Result
import ar.com.midinero.core.hide
import ar.com.midinero.core.show
import ar.com.midinero.databinding.FragmentSingUpBinding
import ar.com.midinero.ui.viewmodel.SingUpViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class SingUpFragment : Fragment(R.layout.fragment_sing_up) {

    private val TAG: String = "${MiDineroApp.TAG}${this.javaClass.name}"

    private lateinit var binding: FragmentSingUpBinding

    private val viewModel by viewModels<SingUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSingUpBinding.bind(view)

        onActionSended()

        onSingUpClick()
    }

    fun onActionSended() {
        binding.confirmPassword.setOnEditorActionListener { _, actionId, _ ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                handled = true
                singIn()
            }
            handled
        }
    }

    private fun onSingUpClick() {
        binding.btnSingUp.setOnClickListener {
            singIn()
        }
    }

    private fun singIn() {
        val email = binding.etEmail.editText?.text.toString().trim()
        val password = binding.etPassword.editText?.text.toString().trim()
        val confirmPassword = binding.etConfirmPassword.editText?.text.toString().trim()

        if (validateFields(email, password, confirmPassword)) {
            viewModel.isSingUp().observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Result.Loading -> {
                        binding.btnSingUp.hide()
                        binding.pbSingUp.show()
                    }
                    is Result.Success -> {
                        Log.i(
                            MiDineroApp.TAG,
                            "uid: ${Firebase.auth.currentUser?.uid ?: "No Auth."}"
                        )
                        binding.btnSingUp.show()
                        binding.pbSingUp.hide()
                    }
                    is Result.Failure -> {
                        Log.e(TAG, "error: ${result.exception}")
                        FirebaseCrashlytics.getInstance().recordException(result.exception)
                        binding.btnSingUp.show()
                        binding.pbSingUp.hide()
                    }
                }
            }
            viewModel.singUp(email, password)
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