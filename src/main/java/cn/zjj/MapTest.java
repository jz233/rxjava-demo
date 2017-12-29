package cn.zjj;

import org.junit.Test;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class MapTest {

    @Test
    public void mapTest() {

        Observable.just("12345678").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s.substring(0,4);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

        Observable.just(new int[]{1, 2, 3, 4, 5, 6, 7, 8}).map(new Func1<int[], int[]>() {
            @Override
            public int[] call(int[] ints) {
                return new int[]{1,2,3,4,5};
            }
        }).subscribe(new Action1<int[]>() {
            @Override
            public void call(int[] ints) {
                for (int item : ints) {
                    System.out.print(item + " ");
                }
            }
        });



    }

}
