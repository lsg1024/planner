package com.p3.planner.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.p3.planner.R
import com.p3.planner.adapter.ContactsAdapter
import com.p3.planner.databinding.ActivityMainBinding
import com.p3.planner.ui.addfragment.AddContactFragment
import com.p3.planner.utils.DataStatus
import com.p3.planner.viewmodal.DatabaseViewModel
import com.p3.planner.viewmodal.isVisible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    @Inject
    lateinit var contactsAdapter : ContactsAdapter

    private val viewModel : DatabaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {

            setSupportActionBar(mainToolbar)

            btnShowDialog.setOnClickListener {
                AddContactFragment().show(supportFragmentManager, AddContactFragment().tag)

            }

            viewModel.getAllContacts()
            viewModel.contactList.observe(this@MainActivity){
                when(it.status){
                    DataStatus.Status.LOADING -> {
                        loading.isVisible(true, rvContacts)
                        emptyBody.isVisible(false, rvContacts)
                    }
                    DataStatus.Status.SUCCESS -> {
                        it.isEmpty?.let {
                            showEmpty(it)
                        }
                        loading.isVisible(false, rvContacts)
                        contactsAdapter.differ.submitList(it.data)
                        rvContacts.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = contactsAdapter
                        }
                    }
                    DataStatus.Status.ERROR -> {
                        loading.isVisible(false, rvContacts)
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    fun showEmpty(isShown : Boolean) {
        binding.apply {
            if (isShown) {
                emptyBody.isVisible(true, listBody)
            } else {
                emptyBody.isVisible(false, listBody)
            }
        }
    }
}