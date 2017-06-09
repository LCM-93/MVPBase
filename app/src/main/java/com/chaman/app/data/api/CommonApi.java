package com.chaman.app.data.api;




import com.chaman.app.data.entity.BannerBean;
import com.chaman.app.data.entity.HttpBaseResult;
import com.chaman.app.data.entity.LoanBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by jess on 8/5/16 12:05
 * contact with jess.yan.effort@gmail.com
 */
public interface CommonApi {


    /**
     * 获取热门贷款列表
     *
     * @return
     */
    @GET("product/home")
    Observable<HttpBaseResult<List<LoanBean>>> getHotLoans();


    /**
     * 获取所有banner
     *
     * @return
     */
    @GET("bannerlist")
    Observable<HttpBaseResult<List<BannerBean>>> getBanners();


}
