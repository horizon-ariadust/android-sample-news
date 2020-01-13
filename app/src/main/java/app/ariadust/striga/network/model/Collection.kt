package app.ariadust.striga.network.model

import com.google.gson.annotations.SerializedName

data class Collection<T>(
    @SerializedName(
        value = "data",
        alternate = ["articles", "sources"]
    ) val data: List<T>
)