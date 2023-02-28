package com.p3.planner.repository

import com.p3.planner.database.ContactsDao
import com.p3.planner.database.ContactsEntity
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val dao: ContactsDao) {
    suspend fun saveContact(entity: ContactsEntity) = dao.saveContact(entity)

    fun getAllContacts()= dao.getAllContacts()

}