package com.truevault_rest_api.util;

import android.util.Base64;
import android.util.Patterns;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static boolean isValidUrl(String url) {
        Pattern p = Patterns.WEB_URL;
        Matcher m = p.matcher(url);
        if (m.matches())
            return true;
        else
            return false;
    }

    public static boolean isValidEmail(String url) {
        Pattern p = Patterns.EMAIL_ADDRESS;
        Matcher m = p.matcher(url);
        if (m.matches())
            return true;
        else
            return false;
    }
}
