package cn.zjj;

import org.junit.Test;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

import java.util.List;

public class TakeTest {

    /**
     * 只发射前四个数，并且把这四个数改为6
     */
    @Test
    public void test1() {

        Observable.just(1, 2, 3, 4, 5).take(4).map(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                integer = 6;
                return integer;
            }
        }).buffer(4).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                System.out.println(integers);
            }
        });
    }


    @Test
    public void test2() {
        Observable.just(1, 2, 3, 4, 5).take(4).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });

    }


}
