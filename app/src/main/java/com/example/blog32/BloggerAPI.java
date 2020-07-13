package com.example.blog32;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class BloggerAPI {
    private static final String key="AIzaSyDpkfQERbtOXdwQxXo9GtzNptg0_pWIP-o";
    private static final String url="https://www.googleapis.com/blogger/v3/blogs/1705298580124441003/posts/";
    public static PostService postService=null;
    public static PostService getService()
    {
        if (postService==null)
        {
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            postService=retrofit.create(PostService.class);

        }
        return postService;
    }
    public interface  PostService
    {
        @GET("?key="+key)
        Call<PosList>getPosList();
       // @GET("(postId)/?key="+key)
        //Call<Item>getPostById(@Path("postId") String id);


    }
}
