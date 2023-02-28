package com.p3.planner.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.p3.planner.utils.Constants.CONTACTS_TABLE

@Entity(tableName = CONTACTS_TABLE)
data class ContactsEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String = "",
    var phone: String = "",
)