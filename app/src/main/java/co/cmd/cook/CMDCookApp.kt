package co.cmd.cook

import android.app.Application
import co.cmd.cook.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CMDCookApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CMDCookApp)
            modules(appComponent)
        }
    }
}