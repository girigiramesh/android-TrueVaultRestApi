package com.truevault_rest_api.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.truevault_rest_api.R;
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
public class SignUpActivity extends BaseActivity implements View.OnClickListener {
    private static String TAG = SignUpActivity.class.getName();

    private TextView tv_fullname, tv_gmail, tv_password, tv_confirm, tv_phone;
    private EditText enter_name, enter_gmail, enter_password, enter_confirm, enter_ph_nmbr;
    private Button submit_btn;
    private RadioGroup gender_rg;
    private UserModel userModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        pullViews();
        init();
    }

    @Override
    public void onClick(View view) {
        if (!isNetworkAvailable()) {
            showToast(Constant.CHECK_NETWORK);
            return;
        }
        switch (view.getId()) {
            case R.id.submit_btn:

                if (enter_name.getText().toString().length() == 0) {
                    enter_name.setError(Constant.NAME_NOTEMPTY);
                    return;
                }
                if (enter_gmail.getText().toString().length() == 0) {
                    enter_gmail.setError(Constant.EMAIL_NOTEMPTY);
                    return;
                }
                if (!Util.isValidEmail(enter_gmail.getText().toString())) {
                    enter_gmail.setError(Constant.NOT_VALID);
                    return;
                }

                if (enter_password.getText().toString().length() == 0) {
                    enter_password.setError(Constant.PASSWORD_NOTEMPTY);
                    return;
                }
                if (enter_confirm.getText().toString().length() == 0) {
                    enter_confirm.setError(Constant.CPASSWORD_NOTEMPTY);
                    return;
                }
                if (!enter_confirm.getText().toString().equals(enter_password.getText().toString())) {
                    showToast(Constant.NOT_EQUAL_PASS);
                    return;
                }

                if (gender_rg.getCheckedRadioButtonId() == -1) {
                    enter_confirm.setError(Constant.GENDER);
                    return;
                }
                userModel = new UserModel();
                userModel.setEmail(enter_gmail.getText().toString());
                userModel.setPhone(enter_ph_nmbr.getText().toString());
                userModel.setFullName(enter_name.getText().toString());
                userModel.setGender(gender_rg.getCheckedRadioButtonId() == R.id.male_rb ? Constant.MALE : Constant.FEMALE);

                showProgressDialog(Constant.WAIT);

                RetrofitHandler.getInstance().createUser(
                        enter_gmail.getText().toString(),
                        enter_password.getText().toString(),
                        userModel.toJson().toString(),
                        true).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        dismissProgressDialog();
                        if (response.isSuccess()) {

                            SharedPreferenceManager.getInstance().putString(Constant.LOGIN_USERNAME, enter_gmail.getText().toString());
                            SharedPreferenceManager.getInstance().putString(Constant.LOGIN_PASSWORD, enter_password.getText().toString());

                            //default settings
                            SharedPreferenceManager.getInstance().putBoolean(Constant.SETTING_LINKY_SERVICE, true);
                            SharedPreferenceManager.getInstance().putBoolean(Constant.SETTING_DELETE_DRAFT, true);

                            Log.e(TAG + " create User ", response.message());
                            try {
                                JSONObject responseJsonObject = new JSONObject(response.body());
                                JSONObject userJsonObject = new JSONObject(responseJsonObject.getString("user"));
                                SharedPreferenceManager.getInstance().putString(UserModel.USER_ID, userJsonObject.getString("id"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            SharedPreferenceManager.getInstance().putString(UserModel.USERNAME, enter_gmail.getText().toString());
                            SharedPreferenceManager.getInstance().putString(UserModel.PASSWORD, enter_password.getText().toString());
                            userModel.storeUserInSp();
                            Intent mainIntent = new Intent(SignUpActivity.this, MainActivity.class);
                            mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(mainIntent);
                        } else {
                            Log.e(TAG + " create User ", response.message());
                            showToast("Error creating user..!");
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        dismissProgressDialog();
                        Log.e(TAG + " create User ", t.getLocalizedMessage());
                        showToast("Error creating user..!");
                    }
                });
                break;
            default:
                showToast("Error creating user..!");
                break;
        }
    }

    public void pullViews() {
        tv_fullname = (TextView) findViewById(R.id.tv_fullname);
        tv_gmail = (TextView) findViewById(R.id.tv_gmail);
        tv_password = (TextView) findViewById(R.id.tv_password);
        tv_confirm = (TextView) findViewById(R.id.tv_confirm);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        enter_name = (EditText) findViewById(R.id.enter_name);
        enter_gmail = (EditText) findViewById(R.id.enter_gmail);
        enter_password = (EditText) findViewById(R.id.enter_password);
        enter_confirm = (EditText) findViewById(R.id.enter_confirm);
        enter_ph_nmbr = (EditText) findViewById(R.id.enter_ph_nmbr);
        submit_btn = (Button) findViewById(R.id.submit_btn);
        gender_rg = (RadioGroup) findViewById(R.id.gender_rg);

    }

    public void init() {
        submit_btn.setOnClickListener(this);
    }
}
