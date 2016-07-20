package com.example.three.siqiyan.http;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Three on 2016/4/21.
 *
 * Rretrofit 请求
 *
 */
public class HangqingAPI {
    public HanQingService hservice ;
    /**
     * Host 请求
     */
    public static final String BASE_URL2 = "http://android.hicp.net";
    /**
     * 单例模式，获取api
     */
    static  class  ApiHoderH{
    private static HangqingAPI hangqingAPI = new HangqingAPI();
    }
    public static HangqingAPI getAPI(){
        return  ApiHoderH.hangqingAPI;
    }

    public HangqingAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL2)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       hservice = retrofit.create(HanQingService.class);
    }
    /**
     * 获取service的实例
     * @return
     */
    public HanQingService getservice() {
        return hservice;
    }

}
