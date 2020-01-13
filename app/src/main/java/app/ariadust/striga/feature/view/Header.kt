package app.ariadust.striga.feature.view

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import app.ariadust.striga.R
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import kotlinx.android.synthetic.main.model_header.view.*

/**
 * Created by Bobby on 2020-01-13.
 */

@ModelView(defaultLayout = R.layout.model_header)
class Header(context: Context, attrs: AttributeSet) : TextView(context, attrs) {

    @TextProp
    fun title(title: CharSequence) {
        textViewHeader.text = title
    }
}