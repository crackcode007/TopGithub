package com.raikwar.top_github.presenter;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecyclerInterface {

    String JSONURL = "https://github-trending-api.now.sh/";

    @GET("developers?language=java&since=weekly")
    Call<String> getString();

}