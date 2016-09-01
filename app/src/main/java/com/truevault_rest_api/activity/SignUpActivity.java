package com.truevault_rest_api.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.truevault_rest_api.R;
import com.truevault_rest_api.model.UserModel;
import com.truevault_rest_api.util.Util;

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
            showToast("Check your network connection!");
            return;
        }
        switch (view.getId()) {
            case R.id.submit_btn:

                if (enter_name.getText().toString().length() == 0) {
                    enter_name.setError("Should not be empty!");
                    return;
                }
                if (enter_gmail.getText().toString().length() == 0) {
                    enter_gmail.setError("Should not be empty!");
                    return;
                }
                if (!Util.isValidEmail(enter_gmail.getText().toString())) {
                    enter_gmail.setError("Not valid email!");
                    return;
                }

                if (enter_password.getText().toString().length() == 0) {
                    enter_password.setError("Should not be empty!");
                    return;
                }
                if (enter_confirm.getText().toString().length() == 0) {
                    enter_confirm.setError("Should not be empty!");
                    return;
                }
                if (!enter_confirm.getText().toString().equals(enter_password.getText().toString())) {
                    showToast("Password not equal..!");
                    return;
                }

                if (gender_rg.getCheckedRadioButtonId() == -1) {
                    enter_confirm.setError("Select gender!");
                    return;
                }
                userModel = new UserModel();
                userModel.setEmail(enter_gmail.getText().toString());
                userModel.setPhone(enter_ph_nmbr.getText().toString());
                userModel.setFullName(enter_name.getText().toString());
                userModel.setGender(gender_rg.getCheckedRadioButtonId() == R.id.male_rb ? "male" : "female");

                showProgressDialog("Please wait..!");
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
