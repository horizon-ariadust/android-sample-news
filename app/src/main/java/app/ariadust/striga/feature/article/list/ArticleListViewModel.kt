package app.ariadust.striga.feature.article.list

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import app.ariadust.striga.architecture.BaseViewModel
import app.ariadust.striga.model.Article
import app.ariadust.striga.model.ArticleFilter
import app.ariadust.striga.network.NewsApi
import io.reactivex.disposables.CompositeDisposable

private const val PAGE_SIZE = 20

class ArticleListViewModel(
    newsApi: NewsApi,
    articleFilter: ArticleFilter
) : BaseViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val dataSourceFactory: ArticleListDataSource.Factory
    val uiLiveData: LiveData<PagedList<Article>>

    init {
        dataSourceFactory = ArticleListDataSource.Factory(disposables, newsApi, articleFilter)
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE * 2)
            .setEnablePlaceholders(false)
            .build()
        uiLiveData = LivePagedListBuilder<Int, Article>(dataSourceFactory, config).build()
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    fun invalidate() {
        dataSourceFactory.invalidate()
    }
}