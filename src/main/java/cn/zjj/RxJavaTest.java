package cn.zjj;

import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class RxJavaTest {

    @Test
    public void test() {
        String[] names = {"Jason", "Chris", "Brian", "Steven"};

        Observable.from(names).subscribe(new Action1<String>() {
            public void call(String s) {
                System.out.println("Hello " + s + ";");
            }
        });
    }

    @Test
    public void test2() {
        Observable<String> sender = Observable.unsafeCreate(new Observable.OnSubscribe<String>() {
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hi, DouJ");
            }
        });

        Observer<String> observer = new Observer<String>() {
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            public void onError(Throwable e) {
                System.out.println("onError");
            }

            public void onNext(String s) {
                System.out.println(s);
            }
        };
        sender.subscribe(observer);
    }

    @Test
    public void createObservable() {
        Observable<String> observable1 = Observable.unsafeCreate(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("msg1");
                subscriber.onNext("msg2");
                subscriber.onCompleted();

            }
        });

        Observable<String> observable2 = Observable.just("just1", "just2");

        List<String> list = new ArrayList<>();
        list.add("from1");
        list.add("from2");
        list.add("from3");
        Observable<String> observable3 = Observable.from(list);

        /**
         * 有观察者订阅时才创建Observable，并且为每个观察者创建一个新的Observable
         */
        Observable<String> deferObservable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just("deferObservable");
            }
        });

        //per second
        Observable<Long> itvObservable = Observable.interval(1, TimeUnit.SECONDS);

        //10, 11, 12, 13, 14
        Observable<Integer> rangeObs = Observable.range(10, 5);

        // 3s after
        Observable<Long> timerObs = Observable.timer(3, TimeUnit.SECONDS);

        // repeat 3 times
        Observable<String> repeatObs = Observable.just("justRepeat").repeat(3);

    }

    @Test
    public void createObserver() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError" + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext: " + s);
            }
        };
    }

    /**
     * Observer只会接收asyncSubject的onCompleted()被调用前的最后一个数据，即“asyncSubject3”，
     * 如果不调用onCompleted()，Subscriber将不接收任何数据。
     */
    @Test
    public void asyncSubjectTest() {
        AsyncSubject<String> asyncSubject = AsyncSubject.create();
        asyncSubject.onNext("asyncSubject1");
        asyncSubject.onNext("asyncSubject2");
        asyncSubject.onNext("asyncSubject3");
        asyncSubject.onCompleted();

        asyncSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext: " + s);
            }
        });
        asyncSubject.onNext("asyncSubject4");
    }

    /**
     * 不需手动调用onCompleted()
     * 接收的是BehaviorSubject被订阅前发送的最后一个数据，
     * 且之后还会继续接收数据
     */
    @Test
    public void behaviorSubjectTest() {
        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create("default");
        behaviorSubject.onNext("behaviorSubject1");
        behaviorSubject.onNext("behaviorSubject2");

        behaviorSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext: " + s);
            }
        });

        behaviorSubject.onNext("behaviorSubject3");
        behaviorSubject.onNext("behaviorSubject4");
    }

    /**
     * Observer只会接收到PublishSubject被订阅之后发送的数据
     */
    @Test
    public void publishSubjectTest() {
        PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject.onNext("publishSubject1");
        publishSubject.onNext("publishSubject2");

        publishSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext: " + s);
            }
        });
        publishSubject.onNext("publishSubject3");
        publishSubject.onNext("publishSubject4");

    }





}
