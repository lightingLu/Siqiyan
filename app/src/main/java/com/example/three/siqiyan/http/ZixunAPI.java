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
public class ZixunAPI {
    private HService service ;
    /**
     * Host 请求
     */
    public static final String BASE_URL = "http://siqiyan.applinzi.com";
    /**
     * 单例模式，获取api
     */
    static  class  ApiHoder{
    private static ZixunAPI zixunAPI = new ZixunAPI();
    }
    public static  ZixunAPI getAPI(){
        return  ApiHoder.zixunAPI;
    }

    public ZixunAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       service = retrofit.create(HService.class);
    }

    /**
     * 获取service的实例
     * @return
     */
    public HService getService() {
        return service;
    }


}
