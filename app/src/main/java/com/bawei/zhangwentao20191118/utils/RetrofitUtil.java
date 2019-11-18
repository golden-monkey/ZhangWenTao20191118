package com.bawei.zhangwentao20191118.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.bawei.zhangwentao20191118.R;
import com.bawei.zhangwentao20191118.api.Api;
import com.bawei.zhangwentao20191118.app.App;

import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static kotlin.text.Typography.tm;

/**
 * 作者：张文涛
 * 功能：网络工具类
 * 时间：2019年11月18日11:31:30
 */
public class RetrofitUtil {
    // 私有方法
    private static RetrofitUtil mInstance;
    private Retrofit retrofit;
    private SSLContext sc;
    private TrustManager tm;

    // 私有构造
    private RetrofitUtil() {
        try {
            // 创建证书对象，方便管理证书数据
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            // 初始化证书资源就，首次为空
            keyStore.load(null);

            // 校验证书，x.509协议，所有的证书都是通过x.509协议生成的
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(App.context.getResources().openRawResource(R.raw.server));

            // SSL协议入场，看看是不是符合SSL协议标准
            sc = SSLContext.getInstance("TLS");
            //信任证书管理,这个是由我们自己生成的,信任我们自己的服务器证书
            tm = new MyTrustManager(certificate);
            sc.init(null, new TrustManager[]{
                    tm
            }, null);
        }catch (Exception e){
            Log.e("RetrofitUtil: ", "SSL设置错误！");
        }

        // 拦截器
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                /*.sslSocketFactory(sc.getSocketFactory(), (X509TrustManager) tm)
                .hostnameVerifier(new TrustHostnameVerifier())*/
                .build();
        // 网络请求
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    // 公共构造
    public static RetrofitUtil getInstance() {
        if (mInstance != null) {
            synchronized (RetrofitUtil.class) {
                if (mInstance != null) {
                    mInstance = new RetrofitUtil();
                }
            }
        }
        return mInstance;
    }

    // 判断网络
    public boolean isNetwork(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
            networkInfo.isAvailable();
        }
        return false;
    }

    // 动态请求 ApiService
    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

    /**
     * 实现了 X509TrustManager
     * 通过此类中的 checkServerTrusted 方法来确认服务器证书是否正确
     */
    static class MyTrustManager implements X509TrustManager {
        X509Certificate cert;

        MyTrustManager(X509Certificate cert) {
            this.cert = cert;
        }

        /**
         * 信任客户端的
         * @param chain
         * @param authType
         * @throws CertificateException
         */
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // 我们在客户端只做服务器端证书校验。
        }

        /**
         * 信任服务器的
         * @param chain
         * @param authType
         * @throws CertificateException
         */
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // 确认服务器端证书和代码中 hard code 的 CRT 证书相同。
            if (chain[0].equals(this.cert)) {
                Log.i("Jin", "checkServerTrusted Certificate from server is valid!");
                return;// found match
            }
            throw new CertificateException("checkServerTrusted No trusted server cert found!");
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /**
     * 检验主机名
     */
    public static class TrustHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            if (hostname.trim().equals("mobile.bwstudent.com")) {// 必须注意，根据题目要求配置相应主机名(域名或者 IP 地址)
                return true;
            }else{
                return false;
            }
        }
    }
}
