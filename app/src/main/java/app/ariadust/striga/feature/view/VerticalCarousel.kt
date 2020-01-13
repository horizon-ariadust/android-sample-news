package app.ariadust.striga.feature.view

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import app.ariadust.striga.R
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView

@ModelView(saveViewState = true, autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class VerticalCarousel(context: Context) : Carousel(context) {

    init {
        isNestedScrollingEnabled = false
    }

    override fun createLayoutManager(): LayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}