package com.truevault_rest_api.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.truevault_rest_api.R;
import com.truevault_rest_api.networks.RetrofitHandler;
import com.truevault_rest_api.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseActivity extends AppCompatActivity {

    private static String TAG = BaseActivity.class.getName();
    private ProgressDialog dialog = null;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signin);

//        final JSONObject userJson = new JSONObject();
//        try {
//            userJson.put("name", "Ramesh");
//            userJson.put("email", "ramesh.kvana@gmail.com");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//        RetrofitHandler.getInstance().createUser("ram12", "8982", Util.stringToBase64(userJson.toString()), true).enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (response.isSuccess()) {
//                    Log.d(TAG, response.body());
//                    try {
//                        JSONObject jsonObject = new JSONObject(response.body());
//                        JSONObject userJsonObject = jsonObject.optJSONObject("user");
//                         user_id = userJsonObject.optString("user_id");


//                        RetrofitHandler.getInstance().readUser(user_id).enqueue(new Callback<String>() {
//
//                            @Override
//                            public void onResponse(Call<String> call, Response<String> response) {
//                                if (response.isSuccess()) {
//                                    Log.d(TAG, response.body());
//                                } else
//                                    Log.e(TAG, response.message());
//                            }
//
//                            @Override
//                            public void onFailure(Call<String> call, Throwable t) {
//                                Log.d(TAG, t.getLocalizedMessage());
//                            }
//                        });


//                        RetrofitHandler.getInstance().updateUser("hari","1234",Util.stringToBase64(userJson.toString()),true).enqueue(new Callback<String>() {
//                            @Override
//                            public void onResponse(Call<String> call, Response<String> response) {
//                                if (response.isSuccess()) {
//                                    Log.d(TAG, response.body());
//                                } else
//                                    Log.e(TAG, response.message());
//                            }
//                            @Override
//                            public void onFailure(Call<String> call, Throwable t) {
//                                Log.d(TAG, t.getLocalizedMessage());
//                            }
//                        });


                        RetrofitHandler.getInstance().deleteUser(user_id).enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.isSuccess()) {
                                    Log.d(TAG, response.body());
                                } else
                                    Log.e(TAG, response.message());
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Log.d(TAG, t.getLocalizedMessage());
                            }
                        });


//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                } else
//                    Log.e(TAG, response.message());
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.d(TAG, t.getLocalizedMessage());
//            }
//        });

    }
    public void showProgressDialog(String message) {
        if (dialog == null) dialog = new ProgressDialog(this);
        dialog.setMessage(message);

        dialog.setCancelable(true);
        try {
            if (!dialog.isShowing()) dialog.show();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }
    public void dismissProgressDialog() {
        try {
            if (dialog.isShowing()) dialog.dismiss();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    public boolean isNetworkAvailable() {
        ConnectivityManager cn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cn.getActiveNetworkInfo();
        return nf != null && nf.isConnected();
    }
}
