package app.ariadust.striga.di

import app.ariadust.striga.network.NewsApi
import app.ariadust.striga.network.model.adapter.DateTimeTypeAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.joda.time.DateTime
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<Gson> {
        GsonBuilder()
            .registerTypeAdapter(DateTime::class.java, DateTimeTypeAdapter())
            .create()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .header("X-Api-Key", "21615b7466e740d4a2ff4810708f72f3")
                    .build()
                chain.proceed(newRequest)
            }
            .build()
    }

    single<NewsApi> {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create(get<Gson>()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(get<OkHttpClient>())
            .build()
            .create(NewsApi::class.java)
    }
}