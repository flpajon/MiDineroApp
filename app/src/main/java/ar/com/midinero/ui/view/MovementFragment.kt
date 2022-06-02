package ar.com.midinero.ui.view
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ar.com.midinero.MiDineroApp
import ar.com.midinero.R
import ar.com.midinero.databinding.FragmentMovementBinding
import ar.com.midinero.ui.viewmodel.MovementViewModel

class MovementFragment : Fragment(R.layout.fragment_movement) {

    private val TAG: String = "${MiDineroApp.TAG}${this.javaClass.name}"

    private lateinit var binding: FragmentMovementBinding

    private val viewModel by viewModels<MovementViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovementBinding.bind(view)

        onAddMovementClick()

        onCancelMovementClick()
    }

    private fun onCancelMovementClick() {
        TODO("Not yet implemented")
    }

    private fun onAddMovementClick() {
        TODO("Not yet implemented")
    }

}