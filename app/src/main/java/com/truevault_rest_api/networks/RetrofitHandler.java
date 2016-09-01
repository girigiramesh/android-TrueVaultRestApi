package com.truevault_rest_api.networks;

import com.truevault_rest_api.util.StringConverterFactory;
import com.truevault_rest_api.util.Util;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ramesh on 8/31/16.
 */
public class RetrofitHandler {
    private static RetrofitHandler OurInstance = new RetrofitHandler();

    public static final String API_KEY = "a4c46d18-4165-4d8b-bb6e-686e2102831f";
    public static final String AUTHORISATION = "Basic " + Util.stringToBase64(API_KEY + ":").replace("\n", "");

    public static RetrofitHandler getInstance() {
        return OurInstance;
    }

    private Retrofit trueVaultRetrofit = new Retrofit.Builder()
            .baseUrl("https://api.truevault.com")
            .addConverterFactory(StringConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private TrueVaultRestApi trueVaultApiService = trueVaultRetrofit.create(TrueVaultRestApi.class);

    private RetrofitHandler() {
    }

    public Call<String> createUser(String username, String password, String attributes, boolean full) {
        return trueVaultApiService.createUser(AUTHORISATION, username, password, attributes, full);
    }

    public Call<String> readUser(String user_id) {
        return trueVaultApiService.readUser(AUTHORISATION, user_id, true);
    }
    public Call<String> updateUser(String username,String password, String attributes, boolean full){
        return trueVaultApiService.updateUser(AUTHORISATION,username,password,attributes,full);
    }
    public Call<String> deleteUser(String user_id){
        return trueVaultApiService.deleteUser(AUTHORISATION,user_id);
    }


}
