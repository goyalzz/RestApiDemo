package com.kmangutov.restexample.services;

import com.kmangutov.restexample.models.Comment;
import com.kmangutov.restexample.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FORUM_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mForumApi = retrofit.create(ForumApi.class);
    }

    public ForumApi getApi() {

        return mForumApi;
    }

    public interface ForumApi {

        @GET("/posts")
        public Observable<List<Post>>
            getPosts();

        @GET("/posts/{id}")
        public Observable<Post>
            getPost(@Path("id") int postId);

        @GET("/comments")
        public Observable<List<Comment>>
            getComments(@Query("postId") int postId);

        @POST("/posts")
        public Observable<Post>
            postPost(Post post);

        @GET("/posts")
        public Call<List<Post>>
        getPostsWithoutObservable();

        @POST("/posts")
        public Call<Post>
        postPostWithoutObservable(Post post);
    }
}
