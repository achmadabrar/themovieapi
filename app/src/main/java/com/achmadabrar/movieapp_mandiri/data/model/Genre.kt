package com.achmadabrar.movieapp_mandiri.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre (
    val id: Long,
    val name: String
): Parcelable