package app.ariadust.striga.feature.article.list

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
import app.ariadust.striga.model.Article
import app.ariadust.striga.model.ArticleFilter
import kotlinx.android.synthetic.main.fragment_article_list.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

private const val EXTRA_ARTICLE_FILTER = "extraArticleFilter"

class ArticleListFragment : BaseFragment(), ArticleListController.Listener,
    SwipeRefreshLayout.OnRefreshListener {

    companion object {
        fun newInstance(articleFilter: ArticleFilter): ArticleListFragment {
            return ArticleListFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_ARTICLE_FILTER, articleFilter)
                }
            }
        }
    }

    private val articleFilter: ArticleFilter
        get() {
            return arguments?.get(EXTRA_ARTICLE_FILTER) as ArticleFilter
        }
    private val viewModel: ArticleListViewModel by inject { parametersOf(articleFilter) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        container.setOnRefreshListener(this)

        val controller = ArticleListController().apply {
            listener = this@ArticleListFragment
        }
        recyclerViewArticleList.setController(controller)
        recyclerViewArticleList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerViewArticleList.addItemDecoration(SpacingItemDecoration())

        viewModel.uiLiveData.observe(viewLifecycleOwner, Observer { pagedList ->
            controller.submitList(pagedList)
        })
    }

    override fun onClickArticle(article: Article) {
        toArticleDetail(article.url)
    }

    override fun onRefresh() {
        viewModel.invalidate()
        container.isRefreshing = false
    }

    private fun toArticleDetail(url: String) {
        context?.openWebView(url)
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