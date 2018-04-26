package com.lcm.app.data.api;




import com.lcm.app.data.entity.HttpBaseResult;
import com.lcm.app.data.entity.WelfareBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface CommonApi {


    /**
     * 获取更新干货的历史记录
     *
     * @return
     */
    @GET("day/history")
    Observable<HttpBaseResult<List<String>>> getHistoryDateList();


    /**
     * @return
     */
    @GET("random/data/福利/2")
    Observable<HttpBaseResult<List<WelfareBean>>> getSplash();
}
