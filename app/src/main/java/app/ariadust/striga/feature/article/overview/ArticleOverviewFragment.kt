package app.ariadust.striga.feature.article.overview

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.ariadust.striga.R
import app.ariadust.striga.architecture.view.BaseFragment
import app.ariadust.striga.extension.dpToPx
import app.ariadust.striga.extension.openWebView
import app.ariadust.striga.feature.article.list.articleListIntent
import app.ariadust.striga.model.Article
import app.ariadust.striga.model.ArticleFilter
import app.ariadust.striga.model.Source
import kotlinx.android.synthetic.main.fragment_acrticle_overview.*
import org.koin.android.ext.android.inject


class ArticleOverviewFragment : BaseFragment(), ArticleOverviewController.Listener,
    SwipeRefreshLayout.OnRefreshListener {

    private val viewModel: ArticleOverviewViewModel by inject()

    companion object {
        fun newInstance(): ArticleOverviewFragment {
            return ArticleOverviewFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_acrticle_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        container.setOnRefreshListener(this)

        val controller = ArticleOverviewController().apply {
            listener = this@ArticleOverviewFragment
        }
        recyclerViewArticleOverview.setController(controller)
        recyclerViewArticleOverview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerViewArticleOverview.addItemDecoration(SpacingItemDecoration())

        viewModel.uiLiveData.observe(viewLifecycleOwner, Observer {
            controller.setData(it)
        })
    }

    override fun onClickArticle(article: Article) {
        toArticleDetail(article.url)
    }

    override fun onClickSource(source: Source) {
        toArticleList(source.id)
    }

    override fun onRefresh() {
        viewModel.invalidate()
        container.isRefreshing = false
    }

    private fun toArticleDetail(url: String) {
        context?.openWebView(url)
    }

    private fun toArticleList(sourceId: String) {
        val articleFilter = ArticleFilter(sourceId)
        startActivity(context?.articleListIntent(articleFilter))
    }
}

private class SpacingItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        if (position == 0) {
            outRect.set(10.dpToPx(), 10.dpToPx(), 10.dpToPx(), 10.dpToPx())
        } else {
            outRect.set(10.dpToPx(), 0, 10.dpToPx(), 10.dpToPx())
        }
    }
}