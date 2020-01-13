package app.ariadust.striga.extension

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

fun Context.openWebView(url: String) {
    CustomTabsIntent.Builder().build().also {
        it.launchUrl(this, Uri.parse(url))
    }
}