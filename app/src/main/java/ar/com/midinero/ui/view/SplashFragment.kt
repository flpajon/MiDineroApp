package ar.com.midinero.ui.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ar.com.midinero.MiDineroApp
import ar.com.midinero.R
import ar.com.midinero.core.Result
import ar.com.midinero.core.hide
import ar.com.midinero.core.show
import ar.com.midinero.databinding.FragmentSplashBinding
import ar.com.midinero.ui.viewmodel.SplashViewModel
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val TAG: String = "${MiDineroApp.TAG}${this.javaClass.name}"

    private lateinit var binding: FragmentSplashBinding

    private val viewModel by viewModels<SplashViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)

        init()

    }

    private fun init() {
        viewModel.init().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.pbValidateUserAuth.show()
                    binding.tvAppName.show()
                }
                is Result.Success -> {
                    if (result.data.second) {
                        goToHome()
                    } else {
                        goToLogInViewPager()
                    }
                    binding.pbValidateUserAuth.hide()
                    binding.tvAppName.hide()
                }
                is Result.Failure -> {
                    Log.e(TAG, "error: ${result.exception}")
                    FirebaseCrashlytics.getInstance().recordException(result.exception)
                    goToLogInViewPager()
                    binding.pbValidateUserAuth.hide()
                    binding.tvAppName.hide()
                }
            }
        }
    }

    private fun goToHome() {
        findNavController().navigate(
            SplashFragmentDirections.actionSplashFragmentToHomeFragment()
        )
    }

    private fun goToLogInViewPager() {
        findNavController().navigate(
            SplashFragmentDirections.actionSplashFragmentToLogInViewPagerFragment()
        )
    }

    override fun onPause() {
        Log.i(TAG, "onPause: Pauso")
        super.onPause()
    }

    override fun onStop() {
        Log.i(TAG, "onStop: Freno")
        super.onStop()
    }

}