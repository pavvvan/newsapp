package com.jspavan.newsapp.network;
import com.jspavan.newsapp.modelClass.NewsResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface NetworkInterface {


    @GET("/v2/top-headlines")
    Observable<NewsResponse> getNews(@Query("country") String country, @Query("category") String category, @Query("apiKey") String apiKey);


}


