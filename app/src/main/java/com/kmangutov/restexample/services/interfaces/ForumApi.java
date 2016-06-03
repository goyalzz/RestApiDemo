package com.kmangutov.restexample.services.interfaces;

import com.kmangutov.restexample.models.Comment;
import com.kmangutov.restexample.models.Post;
import com.kmangutov.restexample.presenters.ListPresenter;
import com.kmangutov.restexample.services.ForumService;

import java.util.List;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by goyalzz on 1/6/16.
 */
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