package com.example.imageloader.ImageUpoad;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface ApiConfig {
  static String BASE_URL="software.hinext.net";
    @Multipart
    @POST("/record/create/image1")
    Call<ServerResponse> uploadImage(@Part MultipartBody.Part image);

   @POST
   Call<Object> uploadImage(@Url String url, @Part MultipartBody.Part image);
}
