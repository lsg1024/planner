package com.p3.planner.ui.addfragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.p3.planner.R
import com.p3.planner.database.ContactsEntity
import com.p3.planner.databinding.FragmentAddContactBinding
import com.p3.planner.viewmodal.DatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddContactFragment : DialogFragment() {

    @Inject
    lateinit var entity: ContactsEntity

    private lateinit var binding: FragmentAddContactBinding

    private val viewModel : DatabaseViewModel by viewModels()

    private var contactId = 0
    private var name = ""
    private var phone = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddContactBinding.inflate(layoutInflater, container, false)
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        contactId = arguments?.getInt(BUNDLE_ID) ?: 0
//        if (contactId > 0) {
//            type
//        }

        binding.apply {
            imgClose.setOnClickListener {
                dismiss()
            }

            btnSave.setOnClickListener {

                name = edtName.text.toString()
                phone = edtPhone.text.toString()

                if (name.isEmpty() || phone.isEmpty()){
                    Snackbar.make(it, "Name and Phone cannot be empty", Snackbar.LENGTH_SHORT).show()
                } else {
                    entity.id = contactId
                    entity.name = name
                    entity.phone = phone

                    viewModel.saveContact(entity)
                    edtName.setText("")
                    edtPhone.setText("")

                    dismiss()
                }
            }
        }
    }
}