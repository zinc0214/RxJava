package ayhan.com.rxjavapractice.volleyexam;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.androidexam2.LogAdapter;
import ayhan.com.rxjavapractice.databinding.VolleyLayoutBinding;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HanAYeon on 2018. 5. 10..
 */


/*
* Volley 사용법 :
*   1. RequestQueue 생성
*   2. Request Object 생성
*   3. Request Object 를 RequestQueue 에 추가
*   4. 설정한 Callback 으로 응답 수신 */
public class VolleyActivity extends AppCompatActivity {

    private VolleyLayoutBinding volleyLayoutBinding;

    private static final String jsonURL = "http://time.jsontest.com/";

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private LogAdapter logAdapter;
    private List<String> logs;

    @Override
    protected void onCreate(Bundle savedInsatnceState) {

        super.onCreate(savedInsatnceState);
        LocalVolley.init(getApplicationContext());

        volleyLayoutBinding = DataBindingUtil.setContentView(this, R.layout.volley_layout);

        volleyLayoutBinding.vfBtnGet.setOnClickListener(l -> post(getObservable()));
        volleyLayoutBinding.vfBtnGet2.setOnClickListener(l -> post(getObservableFromCallable()));
        volleyLayoutBinding.vfBtnGet3.setOnClickListener(l -> post(getObservableFromFuture()));

        setupLogger();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }


    private void post(Observable<JSONObject> observable) {
        DisposableObserver<JSONObject> observer = getObserver();

        compositeDisposable.add(
                observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer)
        );
    }


    // 데이터를 받아오는 방법1
    private Observable<JSONObject> getObservable() {
        //defer 는 대표적은 비동기 함수
        return Observable.defer(() -> {         //defer() : 비동기로 Observable 을 생성하고 하위 스트림에서 사용할 수 있도록 해준다.
            try {
                return Observable.just(getData()); // just() 를 사용하여 새로운 Observable 을 생성하는 이유는 내부적으로 예외처리를 할 수 없기 때문이다.
            } catch (InterruptedException e) {
                log("error : " + e.getMessage());
                return Observable.error(e);
            } catch (ExecutionException e) {
                log("error : " + e.getCause());
                return Observable.error(e.getCause());
            }
        });
    }


    // 데이터를 받아오는 방법2
    /**
     * public static <T> Observable<T> fromCallable(Callable<? extends T> supplier)
     * defer + just 과 같은 효과를 제공.
     * <p>
     * Returns an Observable that, when an observer subscribes to it,
     * invokes a function you specify and then emits the value returned from that function.
     * This allows you to defer the execution of the function you specify until an observer subscribes to the ObservableSource.
     * That is to say, it makes the function "lazy."
     *
     * @return
     */
    // fromCallable 함수는 defer 함수와 달리 어떠한 데이터 타입도 사용할 수 있다.
    // 사용을 위해서는 Future 객체를 직접 전달하지 않고 Future.get() 을 사용해야 한다.
    private Observable<JSONObject> getObservableFromCallable() {
        return Observable.fromCallable(this::getData);  // 중복으로 Observable 을 사용하지 않는다. 비동기.
    }


    // 데이터를 받아오는 방법3
    /**
     * Converts a Future into an ObservableSource.
     * <p>
     * You can convert any object that supports the Future interface into an ObservableSource
     * that emits the return value of the Future.get() method of that object,
     * by passing the object into the from method.
     * <p>
     * Important note: This ObservableSource is blocking; you cannot dispose it.
     */
    private Observable<JSONObject> getObservableFromFuture() {
        return Observable.fromFuture(getFuture()); // fromFuture() : 비동기 계산에 이용하며, get() 메소드를 호출하면 Callable 객체에서 구현한 계산 결과가 나올 때 까지 블로킹 된다.
    }

    private JSONObject getData() throws ExecutionException, InterruptedException {
        return getFuture().get();
    }

    /**
     * Converts the Asynchronous Request into a Synchronous Future that can be used to block via
     * {@code Future.get()}. Observables require blocking/synchronous functions
     * 2. Request Object 생성 3.RequestQueue 에 추가 4. Callback 등록
     */
    private RequestFuture<JSONObject> getFuture() { //생성한 JsonObjectRequest 객체를 RequestQueue 에 추가한다.
        RequestFuture<JSONObject> future = RequestFuture.newFuture();

        //파라미터 : url, jsonRequest, listener, errorListener 여기서ㄴ는 에러과 결과 리스너에 동일한 RequestFuture 콜백을 설절하여 모두 RequestFuture 객체로 받을 수 있게한다.
        JsonObjectRequest request = new JsonObjectRequest(jsonURL, null, future, future);
        LocalVolley.getRequestQueue().add(request);
        return future;

    }

    private DisposableObserver<JSONObject> getObserver() {
        return new DisposableObserver<JSONObject>() {
            @Override
            public void onNext(JSONObject jsonObject) {
                log(" >> " + jsonObject.toString());
            }

            @Override
            public void onError(Throwable e) {
                log(e.toString());
            }

            @Override
            public void onComplete() {
                log("complete");
            }
        };
    }

    private void log(String log) {
        logs.add(log);
        logAdapter.clear();
        logAdapter.addAll(logs);
    }

    private void setupLogger() {
        logs = new ArrayList<>();
        logAdapter = new LogAdapter(this, new ArrayList<>());
        volleyLayoutBinding.vfLvLog.setAdapter(logAdapter);
    }
}



