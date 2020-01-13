package app.ariadust.striga.feature.article.view

import app.ariadust.striga.R
import app.ariadust.striga.architecture.view.epoxy.BaseEpoxyHolder
import app.ariadust.striga.extension.dpToPx
import app.ariadust.striga.model.Article
import coil.api.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import kotlinx.android.synthetic.main.model_article_big.view.*

@EpoxyModelClass(layout = R.layout.model_article_big)
abstract class ArticleBigModel : EpoxyModelWithHolder<ArticleBigModel.ViewHolder>() {

    @EpoxyAttribute
    lateinit var article: Article
    @EpoxyAttribute
    var listener: Listener? = null

    override fun bind(holder: ViewHolder) {
        holder.bind(article, listener)
    }

    class ViewHolder : BaseEpoxyHolder() {

        fun bind(article: Article, listener: Listener?) {
            with(itemView) {
                imageViewCover.load(article.coverUrl) {
                    crossfade(true)
                    scale(Scale.FIT)
                    transformations(RoundedCornersTransformation(5.dpToPx().toFloat()))
                }
                textViewTitle.text = article.title
                textViewDescription.text = article.description
                setOnClickListener { listener?.onClickArticle(article) }
            }
        }
    }

    interface Listener {

        fun onClickArticle(article: Article)
    }
}