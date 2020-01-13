package app.ariadust.striga.architecture.view.epoxy

import android.view.View
import com.airbnb.epoxy.EpoxyHolder

/**
 * Created by Bobby on 2020-01-13.
 */

abstract class BaseEpoxyHolder : EpoxyHolder() {

    protected lateinit var itemView: View

    override fun bindView(itemView: View) {
        this.itemView = itemView
    }
}