package cn.tonyandmoney.rn.webview;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import java.io.File;

import ren.yale.android.cachewebviewlib.CacheType;
import ren.yale.android.cachewebviewlib.WebViewCacheInterceptor;
import ren.yale.android.cachewebviewlib.WebViewCacheInterceptorInst;

/**
 * 缓存配置
 */
public interface CacheWebViewConfig {


    default void webViewConfig(Context context) {
        WebViewCacheInterceptor.Builder builder = new WebViewCacheInterceptor.Builder(context);
        builder.setCachePath(new File(getDiskCacheDir(context), "h5"))//设置缓存路径，默认getCacheDir，名称CacheWebViewCache
                .setCacheSize(1024 * 1024 * 100)//设置缓存大小，默认100M
                .setConnectTimeoutSecond(20)//设置http请求链接超时，默认20秒
                .setReadTimeoutSecond(20)//设置http请求链接读取超时，默认20秒
                .setCacheType(CacheType.NORMAL);//设置缓存为正常模式，默认模式为强制缓存静态资源
        WebViewCacheInterceptorInst.getInstance().init(builder);
    }


    default String getDiskCacheDir(Context context) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }
}
