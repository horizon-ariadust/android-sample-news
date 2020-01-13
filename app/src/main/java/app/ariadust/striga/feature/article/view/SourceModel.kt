package app.ariadust.striga.feature.article.view

import app.ariadust.striga.R
import app.ariadust.striga.architecture.view.epoxy.BaseEpoxyHolder
import app.ariadust.striga.model.Source
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import kotlinx.android.synthetic.main.model_source.view.*

@EpoxyModelClass(layout = R.layout.model_source)
abstract class SourceModel : EpoxyModelWithHolder<SourceModel.ViewHolder>() {

    @EpoxyAttribute
    lateinit var source: Source
    @EpoxyAttribute
    var listener: Listener? = null

    override fun bind(holder: ViewHolder) {
        holder.bind(source, listener)
    }

    class ViewHolder : BaseEpoxyHolder() {

        fun bind(source: Source, listener: Listener?) {
            with(itemView) {
                textViewName.text = source.name
                setOnClickListener {
                    listener?.onClickSource(source)
                }
            }
        }
    }

    interface Listener {

        fun onClickSource(source: Source)
    }
}