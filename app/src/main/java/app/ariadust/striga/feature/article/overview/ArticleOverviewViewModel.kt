package app.ariadust.striga.feature.article.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.ariadust.striga.architecture.BaseViewModel
import app.ariadust.striga.model.Article
import app.ariadust.striga.model.Source
import app.ariadust.striga.network.NewsApi
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction

class ArticleOverviewViewModel(
        private val newsApi: NewsApi
) : BaseViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()
    private val _uiLiveData: MutableLiveData<ArticleOverviewUiModel> = MutableLiveData()
    val uiLiveData: LiveData<ArticleOverviewUiModel>
        get() = _uiLiveData
    private var uiModel: ArticleOverviewUiModel = ArticleOverviewUiModel()
        set(value) {
            field = value
            _uiLiveData.postValue(value)
        }

    init {
        uiModel = ArticleOverviewUiModel()
        loadData()
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    private fun loadData() {
        disposables.add(
                Observable.combineLatest(
                        getTopHeadlines().toObservable(), getSources().toObservable(),
                        BiFunction<List<Article>, List<Source>, ArticleOverviewUiModel> { topHeadlines, sources ->
                            uiModel.copy(
                                    topHeadlines = topHeadlines,
                                    sources = sources
                            )
                        }
                ).subscribe({
                    uiModel = it.copy(isLoading = false)
                }, {
                    it.printStackTrace()
                })
        )
    }

    private fun getTopHeadlines(): Single<List<Article>> {
        return newsApi.getTopHeadlines()
                .map { collection -> collection.data.take(5).map { Article(it) } }
    }

    private fun getSources(): Single<List<Source>> {
        return newsApi.getSources()
                .map { collection -> collection.data.map { Source(it) } }
    }

    fun invalidate() {
        uiModel = ArticleOverviewUiModel()
        loadData()
    }
}