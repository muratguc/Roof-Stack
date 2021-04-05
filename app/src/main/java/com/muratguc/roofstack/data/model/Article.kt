package com.muratguc.roofstack.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Murat Güç on 4/4/2021.
 */

@Parcelize
data class Article (
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
): Parcelable