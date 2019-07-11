package com.example.photogrid.Api
import com.example.photogrid.photoModel.Photo
import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface PhotoService {
    @GET("photos")
    fun getPhotos() : Observable<ArrayList<Photo>>

    companion object {
        fun create(): PhotoService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()
            return retrofit.create(PhotoService::class.java)
        }
    }

}


