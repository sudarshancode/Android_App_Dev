package com.example.retrofitrestapi.Network;

import com.example.retrofitrestapi.Model.PhotoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotoServiceApi {
    @GET("photos")
    Call<List<PhotoModel>> getAllPhotos();

}
