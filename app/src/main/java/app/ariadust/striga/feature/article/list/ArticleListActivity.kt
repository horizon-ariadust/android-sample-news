package app.ariadust.striga.feature.article.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import app.ariadust.striga.R
import app.ariadust.striga.architecture.view.BaseActivity
import app.ariadust.striga.model.ArticleFilter

private const val EXTRA_ARTICLE_FILTER = "extraArticleFilter"

class ArticleListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_list)

        title = getString(R.string.article_list_title)

        val articleFilter: ArticleFilter =
            checkNotNull(intent.getParcelableExtra(EXTRA_ARTICLE_FILTER)) {
                "Article filter must not be null!"
            }

        supportFragmentManager.beginTransaction()
            .add(R.id.container, ArticleListFragment.newInstance(articleFilter))
            .commit()
    }
}

fun Context.articleListIntent(articleFilter: ArticleFilter): Intent {
    return Intent(this, ArticleListActivity::class.java).apply {
        putExtras(Bundle().apply {
            putExtra(EXTRA_ARTICLE_FILTER, articleFilter)
        })
    }
}