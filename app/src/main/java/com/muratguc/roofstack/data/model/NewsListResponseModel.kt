package com.muratguc.roofstack.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Murat Güç on 2/1/2021.
 */
@Parcelize
data class NewsListResponseModel(
    val totalResults: Int,
    val articles: List<Article>
): Parcelable
