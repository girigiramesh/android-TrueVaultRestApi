package com.truevault_rest_api.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.truevault_rest_api.R;
import com.truevault_rest_api.constant.Constant;

/**
 * Created by Ramesh on 9/1/16.
 */
public class MainActivity extends BaseActivity {

    public static final String TAG = MainActivity.class.getName();
    private Button btn_frt, btn_login, btn_signup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        //pull views
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_frt = (Button) findViewById(R.id.btn_frt);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new intent to open the {@link loginActivity}
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);

                // Start the new activity
                startActivity(loginIntent);
                showToast(Constant.SIGNIN);
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(Constant.SIGNUP);            }
        });

        btn_frt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(Constant.FORGOTPASSWORD);            }
        });
    }
}
