package com.myzhsh.utils;

import android.content.Context;

/**
 * Created by yh on 2016/6/3.
 * 网络缓存帮助类
 */
public class CacheUtils {

    public static void putCache(Context context, String url, String data) {
        PrefUtils.putString(url, data, context);
    }

    public static String getCache(Context context, String url) {
        return PrefUtils.getString(url, null, context);
    }
}
