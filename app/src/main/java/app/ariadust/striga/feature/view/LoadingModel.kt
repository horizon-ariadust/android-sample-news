package app.ariadust.striga.feature.view

import app.ariadust.striga.R
import app.ariadust.striga.architecture.view.epoxy.BaseEpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@EpoxyModelClass(layout = R.layout.model_loading)
abstract class LoadingModel : EpoxyModelWithHolder<LoadingModel.ViewHolder>() {

    class ViewHolder : BaseEpoxyHolder()
}