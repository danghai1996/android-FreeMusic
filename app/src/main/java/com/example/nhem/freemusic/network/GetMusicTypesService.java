package com.example.nhem.freemusic.network;

import com.example.nhem.freemusic.network.json_model.MainObjectJSON;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by NHEM on 12/10/2017.
 */

public interface GetMusicTypesService {
    @GET("api")
    Call<MainObjectJSON> getMusicTypes();
}