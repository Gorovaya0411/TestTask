package com.example.test_task.data.apiServiсe

import com.example.test_task.data.model.Data
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface GalleryApiService {
    @GET("photos")
    fun getImage(
        @Query("new") new: Boolean,
        @Query("popular") popular: Boolean,
        @Query("limit") limit: Int = 10,
        @Query("page") page: Int
    ): Observable<Data>

    companion object Factory {
        fun create(): GalleryApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://gallery.dev.webant.ru/api/")
                .build()

            return retrofit.create(GalleryApiService::class.java);
        }
    }
}