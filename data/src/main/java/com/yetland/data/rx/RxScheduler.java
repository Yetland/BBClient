package com.yetland.data.rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author YETLAND
 * @date 2018/10/9 10:15
 */
public class RxScheduler {
    public static <T> Observable.Transformer<T, T> main() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
