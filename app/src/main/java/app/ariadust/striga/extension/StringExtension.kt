package app.ariadust.striga.extension

import android.annotation.SuppressLint
import java.text.Normalizer

@SuppressLint("DefaultLocale")
fun String.slugify(replacement: String = "-") = Normalizer
    .normalize(this, Normalizer.Form.NFD)
    .replace("[^\\p{ASCII}]".toRegex(), "")
    .replace("[^a-zA-Z0-9\\s]+".toRegex(), "").trim()
    .replace("\\s+".toRegex(), replacement)
    .toLowerCase()