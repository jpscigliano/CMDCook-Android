package co.cmd.cook

import android.app.Application
import co.cmd.cook.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class CMDCookApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidContext(this@CMDCookApp)
            modules(appComponent)
        }
    }
}