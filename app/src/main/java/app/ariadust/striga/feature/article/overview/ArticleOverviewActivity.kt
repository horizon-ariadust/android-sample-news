package app.ariadust.striga.feature.article.overview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import app.ariadust.striga.R
import app.ariadust.striga.architecture.view.BaseActivity

class ArticleOverviewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_overview)

        title = getString(R.string.article_overview_title)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, ArticleOverviewFragment.newInstance())
            .commit()
    }
}

fun Context.articleOverviewIntent(): Intent {
    return Intent(this, ArticleOverviewActivity::class.java)
}