package com.truevault_rest_api.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.truevault_rest_api.constant.Constant;
import com.truevault_rest_api.manager.SharedPreferenceManager;
import com.truevault_rest_api.model.UserModel;
import com.truevault_rest_api.networks.RetrofitHandler;
import com.truevault_rest_api.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ramesh on 9/1/16.
 */
public class LoginService extends IntentService {
    public static final String TAG = LoginService.class.getName();
    public static final String ACTION_LOGIN = "com.truevault_rest_api.service.action.LOGIN";

    private static final String EXTRA_USERNAME = "com.truevault_rest_api.service.extra.USERNAME";
    private static final String EXTRA_PASSWORD = "com.truevault_rest_api.service.extra.PASSWORD";

    public LoginService() {
        super(TAG);
    }


    public static void startActionFoo(Context context, String userName, String password) {
        Intent intent = new Intent(context, LoginService.class);
        intent.setAction(ACTION_LOGIN);
        intent.putExtra(EXTRA_USERNAME, userName);
        intent.putExtra(EXTRA_PASSWORD, password);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_LOGIN.equals(action)) {
                final String userName = intent.getStringExtra(EXTRA_USERNAME);
                final String password = intent.getStringExtra(EXTRA_PASSWORD);
                handleActionLogin(userName, password);
            }
        }
    }
    private void handleActionLogin(final String userName, final String password) {
        RetrofitHandler.getInstance().loginUser(
                userName,
                password,
                RetrofitHandler.ACCOUNT_ID).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccess()) {
                    SharedPreferenceManager.getInstance().putString(Constant.LOGIN_USERNAME, userName);
                    SharedPreferenceManager.getInstance().putString(Constant.LOGIN_PASSWORD, password);
                    try {
                        JSONObject responseJsonObject = new JSONObject(response.body());
                        JSONObject userJsonObject = new JSONObject(responseJsonObject.getString("user"));
                        readUser(userJsonObject.getString("id"));
                        SharedPreferenceManager.getInstance().putString(UserModel.USER_ID, userJsonObject.getString("id"));
                        SharedPreferenceManager.getInstance().putString(UserModel.USERNAME, userName);
                        SharedPreferenceManager.getInstance().putString(UserModel.PASSWORD, password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG, response.message());
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG + " login User", t.getLocalizedMessage());
            }
        });
    }
    private void readUser(String userId) {
        RetrofitHandler.getInstance().readUser(userId).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccess()) {
                    try {
                        JSONObject responseJsonObject = new JSONObject(response.body());
                        JSONObject userJsonObject = new JSONObject(responseJsonObject.getString("user"));
                        UserModel.fromJson(Util.base64ToString(userJsonObject.getString("attributes"))).storeUserInSp();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Log.e(TAG + " read User", response.message());
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG + " login User", t.getLocalizedMessage());
            }
        });
    }
}
