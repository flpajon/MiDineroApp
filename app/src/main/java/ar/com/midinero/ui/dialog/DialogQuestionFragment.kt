package ar.com.midinero.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import ar.com.midinero.R
import ar.com.midinero.databinding.DialogQuestionBinding

class DialogQuestionFragment(private val question: String, private val fragment: Fragment) : DialogFragment() {

    private lateinit var binding: DialogQuestionBinding
    internal lateinit var listener: NotificationDialog

    interface NotificationDialog{
        fun onResultDialog(dialog: DialogFragment, result: Boolean)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return fragment.let {
            val builder = AlertDialog.Builder(it.requireContext())
            val inflater = fragment.layoutInflater
            val view = inflater.inflate(R.layout.dialog_question, null)
            binding = DialogQuestionBinding.bind(view)
            binding.tvQuestion.text = question
            builder.setView(view)
                .setPositiveButton(
                    "Aceptar"
                ) { _, _ ->
                    listener.onResultDialog(this,true)
                }
                .setNegativeButton(
                    "Cancelar"
                ) { _, _ ->
                    listener.onResultDialog(this,false)
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as NotificationDialog
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
    }
}