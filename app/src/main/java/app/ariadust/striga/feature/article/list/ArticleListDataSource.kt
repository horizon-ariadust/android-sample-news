package app.ariadust.striga.feature.article.list

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import app.ariadust.striga.model.Article
import app.ariadust.striga.model.ArticleFilter
import app.ariadust.striga.network.NewsApi
import io.reactivex.disposables.CompositeDisposable

private const val INITIAL_PAGE = 1
private const val PAGE_SIZE = 20

class ArticleListDataSource(
    private val disposables: CompositeDisposable,
    private val newsApi: NewsApi,
    private val articleFilter: ArticleFilter
) : PageKeyedDataSource<Int, Article>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Article>
    ) {
        disposables.add(
            newsApi.getNews(
                page = INITIAL_PAGE,
                pageSize = PAGE_SIZE,
                sources = articleFilter.sourceId
            ).subscribe({ collection ->
                val articles = collection.data.map { article -> Article(article) }
                callback.onResult(articles, null, INITIAL_PAGE + 1)
            }, {
                it.printStackTrace()
            })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        disposables.add(
            newsApi.getNews(
                page = params.key,
                pageSize = PAGE_SIZE,
                sources = articleFilter.sourceId
            ).subscribe({ collection ->
                val articles = collection.data.map { article -> Article(article) }
                callback.onResult(articles, params.key + 1)
            }, {
                it.printStackTrace()
            })
        )
    }

    class Factory(
        private val disposables: CompositeDisposable,
        private val newsApi: NewsApi,
        private val articleFilter: ArticleFilter
    ) : DataSource.Factory<Int, Article>() {

        private val dataSourceLiveData = MutableLiveData<ArticleListDataSource>()
        private lateinit var dataSource: ArticleListDataSource

        override fun create(): DataSource<Int, Article> {
            dataSource = ArticleListDataSource(disposables, newsApi, articleFilter)
            dataSourceLiveData.postValue(dataSource)
            return dataSource
        }

        fun invalidate() {
            dataSource.invalidate()
        }
    }
}