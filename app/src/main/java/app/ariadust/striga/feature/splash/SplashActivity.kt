package app.ariadust.striga.feature.splash

import android.os.Bundle
import app.ariadust.striga.R
import app.ariadust.striga.architecture.view.BaseActivity
import app.ariadust.striga.feature.article.overview.articleOverviewIntent
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        disposables.add(
            Observable.timer(3, TimeUnit.SECONDS)
                .subscribe({
                    toArticleOverview()
                }, {
                    it.printStackTrace()
                })
        )
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    private fun toArticleOverview() {
        startActivity(articleOverviewIntent())
    }
}