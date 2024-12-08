package com.example.retrofitapisenddata;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("apidata.php")
    Call<Model> addData(@Field("name") String name,@Field("email") String email);

}
