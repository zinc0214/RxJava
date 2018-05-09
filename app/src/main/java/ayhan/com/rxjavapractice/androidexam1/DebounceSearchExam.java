package ayhan.com.rxjavapractice.androidexam1;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.concurrent.TimeUnit;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.common.Log;
import ayhan.com.rxjavapractice.databinding.DebounceSearchBinding;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by HanAYeon on 2018. 5. 9..
 */
public class DebounceSearchExam extends AppCompatActivity{

    private Disposable disposable;
    private EditText searchBox;
    private DebounceSearchBinding debounceSearchBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        debounceSearchBinding = DataBindingUtil.setContentView(this, R.layout.debounce_search);

        disposable = getObservable()
                .debounce(500, TimeUnit.MILLISECONDS)       // 500 ms 안에 다른 문자를 입력하지 않으면 검색을 시작한다.
                .filter(s -> !TextUtils.isEmpty(s))                 // getObservable 의 onNextChanged 메소드가 호출 된 후 debounce() -> filter() 순으로 리액티브 연산자를 처리한다.
                .observeOn(AndroidSchedulers.mainThread())          // 구독자의 스레드가 안드로이드 UI 스레드 임을 명시한다.
                .subscribeWith(getObserver());

    }


    private Observable<CharSequence> getObservable() {
        return Observable.create(emitter -> searchBox.addTextChangedListener( // searchBox 가 사용자 입력 문자열의 상태 변경을 알 수 있어야 한다.
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    // s : 사용자가 새로 입력한 문자열을 포함하는 EditText 객체의 문자열.
                    // start : 새로 추가된 문자열의 시작 위치값
                    // before : 새 문자열 대신 삭제된 기존 문자열의 수
                    // count : 새로 추가된 문자열의 수
                    public void onTextChanged(CharSequence s, int start, int before, int count) { // 사용자가 문자를 입력하면 해당 메소드가 호출된다.
                        emitter.onNext(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                }
        ));
    }

    private DisposableObserver<CharSequence> getObserver() {
        return new DisposableObserver<CharSequence>() {
            @Override
            public void onNext(CharSequence charSequence) {
                Log.d("Search" + charSequence.toString());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }



}
