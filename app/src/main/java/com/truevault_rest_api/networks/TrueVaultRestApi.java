package com.truevault_rest_api.networks;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ramesh on 8/31/16.
 */
public interface TrueVaultRestApi {

    @POST("/v1/users")
    Call<String> createUser(@Header("Authorization") String authorization, @Query("username") String username, @Query("password") String password, @Query("attributes") String attributes, @Query("full") boolean full);

    @GET("/v1/users/{user_id}")
    Call<String> readUser(@Header("Authorization") String authorization, @Path("user_id") String user_id, @Query("full") boolean full);
    @FormUrlEncoded
    @POST("/v1/auth/login")
    Call<String> loginUser(@Field("username") String username, @Field("password") String password, @Field("account_id") String accountId);

    @GET("/v1/users")
    Call<String> listUser(@Header("Authorization") String authorization,@Query("status") String status,@Query("modified") String modified,@Query("full") boolean full);

    @PUT("/v1/users/{user_id}")
    Call<String> updateUser(@Header("Authorization") String authorization,@Query("username") String username, @Query("password") String password, @Query("attributes") String attributes, @Query("full") boolean full);

    @DELETE("/v1/users/{user_id}")
    Call<String> deleteUser(@Header("Authorization") String authorization,@Path("user_id") String user_id);
}
