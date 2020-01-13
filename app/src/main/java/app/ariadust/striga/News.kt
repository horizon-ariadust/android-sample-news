package app.ariadust.striga

import android.app.Application
import app.ariadust.striga.di.networkModule
import app.ariadust.striga.di.viewModelModule
import net.danlew.android.joda.JodaTimeAndroid
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class News : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
        initJoda()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@News)
            modules(
                networkModule,
                viewModelModule
            )
        }
    }

    private fun initJoda() {
        JodaTimeAndroid.init(this@News)
    }
}