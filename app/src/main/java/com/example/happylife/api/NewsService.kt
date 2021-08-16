package com.example.happylife.api

import android.util.Log
import com.example.happylife.bean.NewsListResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author by syt
 * on 2021/8/13
 * desc:
 */
interface NewsService {

    @GET(ApiConstant.NEWS_LIST)
    suspend fun getNewsList(
        @Query("appId") appId: String,
        @Query("projectId") projectId: String,
        @Query("appKey") appKey: String,
        @Query("modilarId") modilarId: Long,
        @Query("pageNo") pageNo: Int,
        @Query("pageSize") pageSize: Int
    ): NewsListResponse


    //修饰为伴生对象,伴生对象在类中只能存在一个，类似于java中的静态方法 Java 中使用类访问静态成员，静态方法。
    companion object {
        private const val TAG = "NewsService"

        // 1
        fun create(): NewsService {
            val retrofit = Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            return retrofit.create(NewsService::class.java)
        }

        // 2
        private val okHttpClient: OkHttpClient
            get() = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor { chain ->
                    val original = chain.request()
                    val request = original.newBuilder().header("token", "8ed4b3b5aca03567688a98cadbb101826b14c584e4293bd186a94057f3327fa7aa39a262257cff5c104d112604c85bd0a0a8d20f216461383241d71eb9d52f8f1628833993487")
                        .method(original.method, original.body)
                        .build()
                    chain.proceed(request)
                }
                .build()

        // 3
        private val loggingInterceptor: HttpLoggingInterceptor
            get() {
                val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                    override fun log(message: String) {
                        Log.i(TAG, message)
                    }
                })
                interceptor.level = HttpLoggingInterceptor.Level.BASIC
                return interceptor
            }

    }

}