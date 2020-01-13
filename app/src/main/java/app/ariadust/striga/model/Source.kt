package app.ariadust.striga.model

import app.ariadust.striga.network.model.Source as ApiSource

data class Source(
    val id: String,
    val name: String
) {

    constructor(apiSource: ApiSource): this(apiSource.id!!, apiSource.name)
}