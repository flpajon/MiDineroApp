package ar.com.midinero.ui.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ar.com.midinero.MiDineroApp
import ar.com.midinero.R
import ar.com.midinero.core.Result
import ar.com.midinero.databinding.FragmentHomeBinding
import ar.com.midinero.ui.dialog.DialogQuestionFragment
import ar.com.midinero.ui.viewmodel.HomeViewModel
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home){

    private val TAG: String = "${MiDineroApp.TAG}${this.javaClass.name}"

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModels<HomeViewModel>()

    private var clicked: Boolean = false

    private val fromButtom: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.from_bottom_anim
        )
    }

    private val toButtom: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.to_bottom_anim
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        fromButtom.duration = 500
        toButtom.duration = 300

        onMenuOptionClick()

        onAddMovementClick()
//
//        onChageDateClick()
//
//        onShowStatisticsClick()

        onLogOutClick()
    }

    private fun onLogOutClick() {
        binding.btnLogOut.setOnClickListener {
            logOut()
        }
    }

    private fun onShowStatisticsClick() {
        TODO("Not yet implemented")
    }

    private fun onChageDateClick() {
        TODO("Not yet implemented")
    }

    private fun onAddMovementClick() {
        binding.btnAddMovement.setOnClickListener {
            //go to movements screen
        }
    }

    private fun onMenuOptionClick() {
        binding.btnMenuOptions.setOnClickListener {
            animate()
        }
    }

    private fun logOut() {
        viewModel.logOut().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    //loading
                }
                is Result.Success -> {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToLogInViewPagerFragment()
                    )
                }
                is Result.Failure -> {
                    Log.e(TAG, "error: ${result.exception}")
                    FirebaseCrashlytics.getInstance().recordException(result.exception)
                }
            }
        }
    }

    private fun animate() {
        if (!clicked) {
            binding.btnAddMovement.startAnimation(fromButtom)
            binding.btnAddMovement.show()
            binding.btnChangeDate.startAnimation(fromButtom)
            binding.btnChangeDate.show()
            binding.btnShowStatistics.startAnimation(fromButtom)
            binding.btnShowStatistics.show()
            binding.btnLogOut.startAnimation(fromButtom)
            binding.btnLogOut.show()
        } else {
            binding.btnAddMovement.startAnimation(toButtom)
            binding.btnAddMovement.hide()
            binding.btnChangeDate.startAnimation(toButtom)
            binding.btnChangeDate.hide()
            binding.btnShowStatistics.startAnimation(toButtom)
            binding.btnShowStatistics.hide()
            binding.btnLogOut.startAnimation(toButtom)
            binding.btnLogOut.hide()
        }
        clicked = !clicked
    }
}