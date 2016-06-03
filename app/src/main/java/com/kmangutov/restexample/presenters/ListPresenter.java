package com.kmangutov.restexample.presenters;

import com.kmangutov.restexample.models.Post;
import com.kmangutov.restexample.services.ForumService;
import com.kmangutov.restexample.views.ListActivity;

import java.util.List;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ankush Goyal on 3/25/15.
 */

public class ListPresenter {

    ListActivity mView;
    ForumService mForum;

    public ListPresenter(ListActivity view, ForumService forum) {

        mView = view;
        mForum = forum;
    }

    public void loadPostsSyncronously() {
        // Synchronous Call in Retrofit 2.0

        try {
            Call<List<Post>> call = mForum.getApi().getPostsWithoutObservable();
            Response<List<Post>> repo = call.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadPostsAsyncronously() {
        // Asynchronous Call in Retrofit 2.0

        Call<List<Post>> call = mForum.getApi().getPostsWithoutObservable();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                mView.displayPosts(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
//        call.cancel();
    }

    public void postPostsSyncronously() {
        // Synchronous Call in Retrofit 2.0

        try {
            Call<Post> call = mForum.getApi().postPostWithoutObservable(new Post());
            Response<Post> repo = call.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void postPostsAsyncronously() {
        // Asynchronous Call in Retrofit 2.0

        Call<Post> call = mForum.getApi().postPostWithoutObservable(new Post());
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    public void loadPosts() {

        try {
            mForum.getApi()
                    .getPosts()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<Post>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(List<Post> posts) {
                            mView.displayPosts(posts);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
