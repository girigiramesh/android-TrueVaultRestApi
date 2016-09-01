package com.truevault_rest_api.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

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

    }
}
