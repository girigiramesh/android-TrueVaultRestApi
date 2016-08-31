package com.truevault_rest_api.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by Ramesh on 8/31/16.
 */
public class Util {

    public static String stringToBase64(String string) {
        byte[] data = new byte[0];
        try {
            data = string.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String base64 = Base64.encodeToString(data, Base64.DEFAULT);
        return base64.trim().replace("\n","");
    }

    public static String base64ToString(String base64) {
        String text = null;
        byte[] data = Base64.decode(base64, Base64.DEFAULT);
        try {
            text = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return text;
    }
}
