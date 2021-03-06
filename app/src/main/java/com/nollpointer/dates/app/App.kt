package com.nollpointer.dates.app

import android.app.Application
import com.flurry.android.FlurryAgent
import com.google.gson.GsonBuilder
import com.nollpointer.dates.api.WikipediaApi
import com.nollpointer.dates.di.AppComponent
import com.nollpointer.dates.di.DaggerAppComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Onanov Aleksey (@onanov)
 */
class App : Application() {

    private lateinit var retrofit: Retrofit

    override fun onCreate() {
        super.onCreate()
        val gson = GsonBuilder()
                .setLenient()
                .create()
        FlurryAgent.Builder()
                .withLogEnabled(true)
                .withCaptureUncaughtExceptions(true)
                .build(this, "52ZN7BKTNFZ8M26Q2VPN")
        retrofit = Retrofit.Builder()
                .baseUrl("https://ru.wikipedia.org")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        api = retrofit.create(WikipediaApi::class.java)
    }

    companion object {
        lateinit var api: WikipediaApi
            private set

        val component: AppComponent by lazy {
            DaggerAppComponent.create()
        }
    }
}