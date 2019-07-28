package com.jspavan.newsapp.presenter;

import android.content.Context;
import android.util.Log;
import com.jspavan.newsapp.modelClass.NewsResponse;
import com.jspavan.newsapp.network.NetworkClient;
import com.jspavan.newsapp.network.NetworkInterface;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

public class NewsPresenter {


    public NewsCallback newsCallback;
    private NetworkClient networkClient;
    private Context context;

    public NewsPresenter(NewsCallback newsCallback) {

        this.newsCallback = newsCallback;
    }


    public void getNewsDetails(String country, String category, String apiKey, final Context context) {
        this.context = context;
        newsCallback.onShowProgressbar();
        getObservalbe(country, category, apiKey).subscribeWith(getObserver());
    }



    private Observable<NewsResponse> getObservalbe(String country, String category, String apiKey) {

        networkClient = new NetworkClient();
        Observable<NewsResponse> loginResponseObservable = networkClient.getRetrofit(context).create(NetworkInterface.class)
                .getNews(country, category, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return loginResponseObservable;
    }


    private DisposableObserver<NewsResponse> getObserver() {
        return new DisposableObserver<NewsResponse>() {
            @Override
            public void onNext(NewsResponse loginResponse) {
                newsCallback.hideProgressbar();
                Log.d(TAG, "onNext: " + loginResponse.getStatus());
                newsCallback.onSuccess(loginResponse);
            }

            @Override
            public void onError(Throwable e) {
                newsCallback.hideProgressbar();
                Log.e(TAG, "onError: " + e.getMessage());
                newsCallback.onFailure(e);
            }

            @Override
            public void onComplete() {
                newsCallback.hideProgressbar();
                Log.d(TAG, "onComplete: ");

            }
        };
    }


    public interface NewsCallback {

        void onSuccess(NewsResponse newsResponse);

        void onFailure(Throwable t);

        void onShowProgressbar();

        void hideProgressbar();
    }
}
