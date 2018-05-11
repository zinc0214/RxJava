package ayhan.com.rxjavapractice.androidexam1;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by HanAYeon on 2018. 5. 9..
 */
public class Helloworld extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        helloWorld1();
        helloWorld2();
    }

    private void helloWorld1() {
        DisposableObserver<String> observable = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                baseLayoutBinding.text1.setText(s);
            }

            @Override
            public void onError(Throwable e) { }

            @Override
            public void onComplete() { }
        };

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            emitter.onNext("Hello World !!");
            emitter.onComplete();
        }).subscribe(observable);
    }


    private void helloWorld2() {
        Observable.just("Hello World???").subscribe(data -> baseLayoutBinding.text2.setText(data));
    }

}
