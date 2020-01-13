package app.ariadust.striga.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleFilter(
    val sourceId: String
) : Parcelable