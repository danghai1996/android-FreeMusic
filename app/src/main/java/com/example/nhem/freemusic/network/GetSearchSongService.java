package com.example.nhem.freemusic.network;

import com.example.nhem.freemusic.network.json_model.SearchSongJSON;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by NHEM on 29/10/2017.
 */

public interface GetSearchSongService {
    @GET("http://103.1.209.134/services/api/audio")
    Call<SearchSongJSON> getSearchSong(@Query("search_terms") String search);
}
