package com.example.three.siqiyan.http;

import com.example.three.siqiyan.bean.NewsInfo;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Three on 2016/4/21.
 * 网络访问请求接口
 */
public interface HService {
    @GET("/Siqiyan/zixun/listnews.json")
    Observable<NewsInfo> getResult();

}
