package com.truevault_rest_api.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.truevault_rest_api.R;
import com.truevault_rest_api.constant.Constant;
import com.truevault_rest_api.manager.SharedPreferenceManager;
import com.truevault_rest_api.service.LoginService;
import com.truevault_rest_api.util.Util;

/**
 * Created by Ramesh on 9/1/16.
 */
public class SignInActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_email, et_password;
    private Button btn_login, btn_signup, btn_frt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        pullViews();
        init();
        if (et_email.getText().length() == 0)
            if (Util.isNotNullAndNotEmpty(SharedPreferenceManager.getInstance().getString(Constant.LOGIN_USERNAME, ""))) {
                et_email.setText(SharedPreferenceManager.getInstance().getString(Constant.LOGIN_USERNAME, ""));
            }
        if (et_password.getText().length() == 0)
            if (Util.isNotNullAndNotEmpty(SharedPreferenceManager.getInstance().getString(Constant.LOGIN_PASSWORD, ""))) {
                et_password.setText(SharedPreferenceManager.getInstance().getString(Constant.LOGIN_PASSWORD, ""));
            }
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        if (!isNetworkAvailable()) {
            showToast(Constant.CHECK_NETWORK);
            return;
        }
        switch (view.getId()) {
            case R.id.btn_login:

                if (et_email.getText().toString().length() == 0) {
                    et_email.setError(Constant.EMAIL_NOTEMPTY);
                    return;
                }

                if (!Util.isValidEmail(et_email.getText().toString())) {
                    et_email.setError(Constant.NOT_VALID);
                    return;
                }
                if (et_password.getText().toString().length() == 0) {
                    et_password.setError(Constant.PASSWORD_NOTEMPTY);
                    return;
                }
                showProgressDialog(Constant.WAIT);
                LoginService.startActionFoo(SignInActivity.this, et_email.getText().toString(), et_password.getText().toString());

                break;
            case R.id.btn_signup:
                Intent signUpIntent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(signUpIntent);
                break;
            case R.id.et_password:
                Intent signIntent = new Intent(SignInActivity.this, ForgotPasswordActivity.class);
                startActivity(signIntent);
                break;
            default:
                showToast("Error creating user..!");
                break;
        }

    }

    public void pullViews() {
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_frt = (Button) findViewById(R.id.btn_frt);
    }
//     initialising listeners to views

    public void init() {
        //click listeners
        btn_frt.setOnClickListener(this);
        btn_signup.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }
}
