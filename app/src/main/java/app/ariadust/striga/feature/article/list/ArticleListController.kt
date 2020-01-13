package app.ariadust.striga.feature.article.list

import app.ariadust.striga.feature.article.view.ArticleBigModel
import app.ariadust.striga.feature.article.view.ArticleBigModel_
import app.ariadust.striga.feature.article.view.ArticleSmallModel
import app.ariadust.striga.feature.article.view.ArticleSmallModel_
import app.ariadust.striga.model.Article
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController

class ArticleListController : PagedListEpoxyController<Article>() {

    var listener: Listener? = null

    override fun buildItemModel(currentPosition: Int, item: Article?): EpoxyModel<*> {
        return if (item != null) {
            if (currentPosition == 0) {
                ArticleBigModel_()
                        .id(item.id)
                        .article(item)
                        .listener(object : ArticleBigModel.Listener {
                            override fun onClickArticle(article: Article) {
                                listener?.onClickArticle(article)
                            }
                        })
            } else {
                ArticleSmallModel_()
                        .id(item.id)
                        .article(item)
                        .listener(object : ArticleSmallModel.Listener {
                            override fun onClickArticle(article: Article) {
                                listener?.onClickArticle(article)
                            }
                        })
            }
        } else {
            throw IllegalStateException("Item can't be null!")
        }
    }

    interface Listener {

        fun onClickArticle(article: Article)
    }
}