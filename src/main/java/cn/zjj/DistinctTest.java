package cn.zjj;

import org.junit.Test;
import rx.Observable;
import rx.functions.Action1;

public class DistinctTest {

    @Test
    public void test() {

        Observable.just(1, 2, 1, 1, 2, 3).distinct().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }
}
