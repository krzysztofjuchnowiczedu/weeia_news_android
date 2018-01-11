package pl.com.juchnowicz.weeianews.services

import io.reactivex.Observable
import pl.com.juchnowicz.weeianews.models.APIResponse
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface APIService {
    @GET("api/v1/getNews")
    fun getNews(): Observable<List<APIResponse.News>>

    companion object {
        fun create(): APIService {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://weeianews.juchnowicz.com.pl/")
                    .build()

            return retrofit.create(APIService::class.java)
        }
    }
}