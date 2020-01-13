package app.ariadust.striga.di

import app.ariadust.striga.feature.article.list.ArticleListViewModel
import app.ariadust.striga.feature.article.overview.ArticleOverviewViewModel
import app.ariadust.striga.model.ArticleFilter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { (articleFilter: ArticleFilter) ->
        ArticleListViewModel(get(), articleFilter)
    }

    viewModel {
        ArticleOverviewViewModel(get())
    }
}