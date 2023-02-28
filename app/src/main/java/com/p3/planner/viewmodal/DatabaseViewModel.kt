package com.p3.planner.viewmodal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p3.planner.database.ContactsEntity
import com.p3.planner.repository.DatabaseRepository
import com.p3.planner.utils.DataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel @Inject constructor(private val repository: DatabaseRepository) : ViewModel(){

    private val _contactList = MutableLiveData<DataStatus<List<ContactsEntity>>>()
    val contactList : LiveData<DataStatus<List<ContactsEntity>>>
    get() = _contactList

    fun saveContact(entity: ContactsEntity) = viewModelScope.launch {
        repository.saveContact(entity)
    }

    fun getAllContacts() = viewModelScope.launch {
        repository.getAllContacts()
            .catch { _contactList.postValue(DataStatus.error(it.message.toString())) }
            .collect{_contactList.postValue(DataStatus.success(it, it.isEmpty()))}
    }
}