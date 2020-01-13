package app.ariadust.striga.feature.article.overview

import app.ariadust.striga.feature.article.view.*
import app.ariadust.striga.feature.view.header
import app.ariadust.striga.feature.view.loading
import app.ariadust.striga.feature.view.verticalCarousel
import app.ariadust.striga.feature.view.verticalGridCarousel
import app.ariadust.striga.model.Article
import app.ariadust.striga.model.Source
import com.airbnb.epoxy.TypedEpoxyController

class ArticleOverviewController : TypedEpoxyController<ArticleOverviewUiModel>() {

    var listener: Listener? = null

    override fun buildModels(data: ArticleOverviewUiModel?) {

        if (data != null) {

            if (data.isLoading) {
                loading {
                    id("loading")
                }
            }

            if (data.topHeadlines != null) {
                header {
                    id("topHeadlinesHeader")
                    title("Top Headlines")
                }
                verticalCarousel {
                    id("topHeadlines")
                    hasFixedSize(true)
                    models(data.topHeadlines.map {
                        ArticleBigModel_()
                            .id(it.id)
                            .article(it)
                            .listener(object : ArticleBigModel.Listener {
                                override fun onClickArticle(article: Article) {
                                    listener?.onClickArticle(article)
                                }
                            })
                    })
                }
            }

            if (data.sources != null) {
                header {
                    id("sourcesHeader")
                    title("Sources")
                }
                verticalGridCarousel {
                    id("sources")
                    hasFixedSize(true)
                    models(data.sources.map {
                        SourceModel_()
                            .id(it.id)
                            .source(it)
                            .listener(object : SourceModel.Listener {
                                override fun onClickSource(source: Source) {
                                    listener?.onClickSource(source)
                                }
                            })
                    })
                }
            }
        }
    }

    interface Listener {

        fun onClickArticle(article: Article)

        fun onClickSource(source: Source)
    }
}