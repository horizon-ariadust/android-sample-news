package app.ariadust.striga.network

import app.ariadust.striga.network.model.Article
import app.ariadust.striga.network.model.Collection
import app.ariadust.striga.network.model.Source
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/everything")
    fun getNews(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("q") query: String? = null,
        @Query("sources") sources: String? = null
    ): Single<Collection<Article>>

    @GET("v2/top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("q") query: String? = null
    ): Single<Collection<Article>>

    @GET("v2/sources")
    fun getSources(): Single<Collection<Source>>
}
