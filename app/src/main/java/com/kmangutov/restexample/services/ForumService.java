package com.kmangutov.restexample.services;

import com.kmangutov.restexample.models.Comment;
import com.kmangutov.restexample.models.Post;
import com.kmangutov.restexample.services.interfaces.ForumApi;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by kmangutov on 3/25/15.
 */

public class ForumService {

    private static final String FORUM_SERVER_URL = "http://jsonplaceholder.typicode.com";
    private ForumApi mForumApi;

    public ForumService() {

//        RequestInterceptor requestInterceptor = new RequestInterceptor() {
//            @Override
//            public void intercept(RequestFacade request) {
//                request.addHeader("Accept", "application/json");
//            }
//        };
//
//        RestAdapter restAdapter = new RestAdapter.Builder()
//                .setEndpoint(FORUM_SERVER_URL)
//                .setRequestInterceptor(requestInterceptor)
//                .setLogLevel(RestAdapter.LogLevel.FULL)
//                .build();
//
//        mForumApi = restAdapter.create(ForumApi.class);

        OkHttpClient client = new OkHttpClient();

// Enable caching for OkHttp
//        int cacheSize = 10 * 1024 * 1024; // 10 MiB
//        Cache cache = new Cache(getApplication().getCacheDir(), cacheSize);
//        client.setCache(cache);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FORUM_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mForumApi = retrofit.create(ForumApi.class);
    }

    public ForumApi getApi() {
        return mForumApi;
    }
}
