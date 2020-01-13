package app.ariadust.striga.model

import app.ariadust.striga.extension.slugify
import app.ariadust.striga.network.model.Article as ApiArticle

data class Article(
    val id: String,
    val title: String,
    val description: String?,
    val coverUrl: String,
    val url: String
) {

    constructor(apiArticle: ApiArticle) : this(
        apiArticle.title.slugify(),
        apiArticle.title,
        apiArticle.description,
        apiArticle.urlToImage,
        apiArticle.url
    )
}