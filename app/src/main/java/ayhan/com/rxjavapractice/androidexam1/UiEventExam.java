package ayhan.com.rxjavapractice.androidexam1;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Random;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.FragmentOnClickBinding;
import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by HanAYeon on 2018. 5. 9..
 */
public class UiEventExam extends AppCompatActivity {

    private FragmentOnClickBinding fragmentOnClickBinding;
    private String text1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentOnClickBinding = DataBindingUtil.setContentView(this, R.layout.fragment_on_click);

        getClickEventObservable()
                .map(s->"clicked")
                .subscribe(getObserver());

        getClickEventObservableWithLambda()
                .map(s->"clicked Lamda")
                .subscribe(getObserver());

        getClickEventObservable()
                .map(local -> 7)
                .flatMap(this::compareNumbers)
                .subscribe(data -> Log.d("uiExamResult : ", data));
    }


    private Observable<View> getClickEventObservable() {
        return Observable.create(emitter
                -> fragmentOnClickBinding.btnClickObserver.setOnClickListener(emitter::onNext));
    }

    private Observable<View> getClickEventObservableWithLambda() {
        return Observable.create(s -> fragmentOnClickBinding.btnClickObserverLambda.setOnClickListener(s::onNext));
    }

    private Observable<String> compareNumbers(int input) {
        return Observable.just(input)
                .flatMap(in -> {
                    Random random = new Random();
                    int data = random.nextInt(10);
                    return Observable.just("local " + in, "remote :" + data, "result =" + (in == data));
                });
    }

    private DisposableObserver<? super String> getObserver() {
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.d("onNext", s);

            }

            @Override
            public void onError(Throwable e) {
               Log.e("error", e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("complete", "");
            }
        };
    }


}

