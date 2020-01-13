package app.ariadust.striga.feature.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar
import app.ariadust.striga.R
import com.airbnb.epoxy.ModelView

@ModelView(defaultLayout = R.layout.model_loading)
class Loading(context: Context, attrs: AttributeSet) : ProgressBar(context, attrs)