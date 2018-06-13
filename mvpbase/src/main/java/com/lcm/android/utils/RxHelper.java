package com.lcm.android.utils;



import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/5/18 18:46
 * Desc:
 * *****************************************************************
 */
public class RxHelper {

    public static <T> ObservableTransformer<T, T> io_main() {
        return tObservable ->
                tObservable.subscribeOn(Schedulers.io())
                        .unsubscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T, T> new_main() {
        return tObervable ->
                tObervable.subscribeOn(Schedulers.newThread())
                        .unsubscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T, T> new_new() {
        return tObervable ->
                tObervable.subscribeOn(Schedulers.newThread())
                        .unsubscribeOn(Schedulers.newThread())
                        .observeOn(Schedulers.newThread());
    }

//    public static <T> ObservableTransformer<HttpResult<T>, T> handleResult() {
//        return resultObservable -> resultObservable.flatMap(tResult -> {
//            int code = Integer.parseInt(tResult.getCode().trim());
//            if (code == 200) {
//                return createData(tResult.getData());
//            } else if (code == 401) {
//                CustomToast.showShort("用户登录过期,请重新登录！");
//                ARouter.getInstance().build(ARouterPath.Login).navigation();
//                return Observable.empty();
//            } else {
//                CustomToast.showShort(tResult.getMessage());
//                return Observable.error(new Exception(tResult.getMessage()));
//            }
//        });
//    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(T data) {
        return Observable.create(subscriber -> {
            try {
                subscriber.onNext(data);
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });

    }
}
