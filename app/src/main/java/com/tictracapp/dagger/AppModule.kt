package com.tictracapp.dagger

import android.app.Application
import com.tictracapp.api.CommonApiInterface
import com.tictracappTest.BuildConfig
import com.tictracappTest.R
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton
import androidx.room.Room
import android.content.Context
import com.tictracapp.data.AppDatabase
import com.tictracapp.data.db.UserDao


@Module(includes = [(ViewModelModule::class)])
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(app: Application): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient().newBuilder()
        if (BuildConfig.DEBUG) builder.addInterceptor(interceptor)
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .client(builder.build())
            .baseUrl(app.getString(R.string.server_url))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideCommonApiService(retrofit: Retrofit): CommonApiInterface {
        return retrofit.create(CommonApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, AppDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideShowDao(appDb: AppDatabase): UserDao {
        return appDb.userDao()
    }

    @Provides
    @Singleton
    fun provideContext(app: Application): Context = app.applicationContext
}