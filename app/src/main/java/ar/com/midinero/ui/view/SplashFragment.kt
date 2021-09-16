package ar.com.midinero.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ar.com.midinero.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        runBlocking { launch { goToLogInViewPager() } }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private suspend fun goToLogInViewPager() {
        delay(3000)
        findNavController().navigate(R.id.action_splashFragment_to_logInViewPagerFragment)
    }

}