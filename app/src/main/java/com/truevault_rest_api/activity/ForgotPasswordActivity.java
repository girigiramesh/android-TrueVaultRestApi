package com.truevault_rest_api.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.truevault_rest_api.R;

/**
 * Created by Ramesh on 9/1/16.
 */
public class ForgotPasswordActivity extends BaseActivity implements View.OnClickListener {
    private TextView text_email;
    private EditText gmail_enter;
    private Button forgot_submit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        pullViews();
        init();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forgot_submit:
                finish();
                break;
            default:
                showToast("Error creating user..!");
                break;
        }
    }

    public void pullViews() {
        text_email = (TextView) findViewById(R.id.text_email);
        gmail_enter = (EditText) findViewById(R.id.gmail_enter);
        forgot_submit = (Button) findViewById(R.id.forgot_submit);
    }

    public void init() {
        forgot_submit.setOnClickListener(this);
    }
}
