package com.saadfauzi.myrecyclerview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AndroidModel(
    val name: String,
    val desc: String,
    val image: String
) : Parcelable