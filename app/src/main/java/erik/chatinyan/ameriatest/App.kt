package erik.chatinyan.ameriatest

import android.app.Application
import erik.chatinyan.ameriatest.di.networkModule
import erik.chatinyan.ameriatest.di.repositoryModule
import erik.chatinyan.ameriatest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)

            modules(
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}