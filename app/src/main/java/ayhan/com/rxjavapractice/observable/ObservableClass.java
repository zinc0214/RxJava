package ayhan.com.rxjavapractice.observable;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;


public class ObservableClass extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String printText = "ObservableClass \n\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        just();
        create();
        fromArray();
        fromIterable();
        fromCallable();
        fromFuture();

        baseLayoutBinding.text1.setText(printText);
    }


    /*
    * just() : 인자로 넣은 데이터를 차례로 발행하기 위해서 사용. 여러 개의 값을 넣을 수 있지만 타입이 모두 동일해야 한다
    * */
    private void just() {

        printText += " just() : 인자로 넣은 데이터를 차례로 발행하기 위해서 사용. 여러 개의 값을 넣을 수 있지만 타입이 모두 동일해야 한다\n";

        Observable.just(1, 2, 3, 4, 5, 6)
                .subscribe(data -> printText += data + "\n");
    }

    /*
    * create() : just 함수는 데이터를 인자로 넣으면 자동으로 알림 이벤트가 발생하지만 create() 함수는 onNext, onComplete, onError 와 같은 알림을
    * 개발자가 직접 호출해야 한다.
    * create() -> onNext() -> onComplete() , onError()
    * onComplete 를 반드시 호출해야만 한다. 또한 subscribe 함수를 호출하지 않으면 아무런 값도 출력되지 않는다.
    * */
    private void create() {

        printText += "\n create() : just 함수는 데이터를 인자로 넣으면 자동으로 알림 이벤트가 발생하지만 create() 함수는 onNext, onComplete, onError 와 같은 알림을 "
                + "개발자가 직접 호출해야 한다.\n" +
                "create() -> onNext() -> onComplete() , onError()\n" +
                "onComplete 를 반드시 호출해야만 한다. 또한 subscribe 함수를 호출하지 않으면 아무런 값도 출력되지 않는다. \n";

        Observable<Integer> source = Observable.create((ObservableEmitter<Integer> emitter) -> {
            emitter.onNext(100);
            emitter.onNext(200);
            emitter.onComplete();
        });
        source.subscribe(data -> printText += data + "\n");
    }

    /*
    * fromArray() : 배열의 데이터를 처리할 때 사용
    * */
    private void fromArray() {

        printText += " fromArray() : 배열의 데이터를 처리할 때 사용\n";

        Integer[] arr = {100, 200, 300};
        Observable<Integer> source = Observable.fromArray(arr);
        source.subscribe(data -> printText += data + "\n");
    }

    /*
    * formIterable() : Iterable 인터페이스를 구현한 클래스에서 Observable 객체를 구현할 수도 있다
    * Iterable 인터페이스는 이터레이터 패턴을 구현한 것으로 다음에 어떤 데이터가 있는지와 그 값을 얻어오는 것에만 관여하고 특정 데이터 타입에 의존하지 않는다.
    * */
    private void fromIterable() {
        printText += "\n" + "formIterable() : Iterable 인터페이스를 구현한 클래스에서 Observable 객체를 구현할 수도 있다\n" +
                "Iterable 인터페이스는 이터레이터 패턴을 구현한 것으로 다음에 어떤 데이터가 있는지와 그 값을 얻어오는 것에만 관여하고 특정 데이터 타입에 의존하지 않는다.\n";


        List<String> names = new ArrayList<>();
        names.add("HyunSoo");
        names.add("JeaHo");
        names.add("ByungGab");

        Observable<String> source = Observable.fromIterable(names);
        source.subscribe(data -> printText+= data + "\n");
    }

    /*
    * fromCallable() : Callable 인퍼테이스는 비동기 실행 후 결과를 반환하는 call() 메소드를 정의한다. Executor 인터페이스의 인자로 활용되기 때문에
    * 잠재적으로 다른 스레드에서 실행되는 것을 의미한다.
    * */
    private void fromCallable() {

        printText += "\n fromCallable() : Callable 인퍼테이스는 비동기 실행 후 결과를 반환하는 call() 메소드를 정의한다. Executor 인터페이스의 인자로 활용되기 때문에 " +
                "잠재적으로 다른 스레드에서 실행되는 것을 의미한다. \n ";

        Callable<String> callable = () -> {
            Thread.sleep(1000);
            return "Hello Callable";
        };

        Observable<String> source = Observable.fromCallable(callable);
        source.subscribe(data -> printText += data + "\n");
    }


    /*
    * fromFuture() : Future 인터페이스는 동시성 API로 비동기 계산의 결과를 구할 때 사용한다. get 메소드를 호출하면 Callable 객체에서 구현한
    * 계산 결과가 나올 때 까지 블로킹(반환한 Future 객체에 get() 메소드를 호출하고 결과값이 나올 때 까지 기다리는 것) 된다.
    * */
    private void fromFuture() {

        printText +=  "\n fromFuture() : Future 인터페이스는 동시성 API로 비동기 계산의 결과를 구할 때 사용한다. get 메소드를 호출하면 Callable 객체에서 구현한 " +
                "계산 결과가 나올 때 까지 블로킹(반환한 Future 객체에 get() 메소드를 호출하고 결과값이 나올 때 까지 기다리는 것) 된다.\n";

        Future<String> future = Executors.newSingleThreadExecutor().submit(() -> {
            Thread.sleep(1000);
            return "Hello Future";
        });

        Observable<String> source = Observable.fromFuture(future);
        source.subscribe(data -> printText +=data + "\n");
    }

}
