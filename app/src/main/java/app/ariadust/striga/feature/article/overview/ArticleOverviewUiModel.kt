package app.ariadust.striga.feature.article.overview

import app.ariadust.striga.model.Article
import app.ariadust.striga.model.Source

data class ArticleOverviewUiModel(
    val topHeadlines: List<Article>? = null,
    val sources: List<Source>? = null,
    val isLoading: Boolean = true
)