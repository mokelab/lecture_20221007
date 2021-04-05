package com.mokelab.mytodo.model.todo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val _id: Int = 0,
    val title: String,
    val detail: String,
    val created: Long,
    val modified: Long
): Parcelable