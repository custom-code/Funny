package com.zhe.funny.main.repository;

import com.zhe.funny.main.interactor.model.MeizhiData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zhe on 16/5/22.
 */
public interface MainService {

    @GET("data/福利/10/{page}")
    Observable<MeizhiData> getMeizhiData(
            @Path("page") int page);

}
