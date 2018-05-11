package ayhan.com.rxjavapractice.androidexam2;

import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by HanAYeon on 2018. 5. 10..
 */
public class AsyncTaskActivity extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInsatnceState) {

        super.onCreate(savedInsatnceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        initAndroidAsync(); // 일반적인 async 방법
        initRxAsync(); // Rx Android 를 사용한 방법
    }

    private void initAndroidAsync() {
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute("Helloooo", "async", "world?");

    }
    public class MyAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            StringBuilder word = new StringBuilder();
            for (String s : params) {
                word.append(s).append(" ");
            }

            return word.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            baseLayoutBinding.text1.setText(result);

        }
    }


    private void initRxAsync() {
        Observable.just("Hellooo", "Rx async", "world!")
                .reduce((x,y) -> x + " " + y)
                .observeOn(AndroidSchedulers.mainThread()) // 사용할 스레드 지정
                //.subscribe(getObserver()) -> 이와 같은 방식으로도 할 수 있다!
                .subscribe(
                        result -> baseLayoutBinding.text2.setText(result),
                        e -> Log.e("AsyncTaskError", e.getMessage()), // 모든 에러는 구독자의 onError() 함수에서 처리할 수 있어야 한다.
                        () -> Log.i("AsyncTaskDone", "done")
                );
    }

    private MaybeObserver<String> getObserver() {
        return new MaybeObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(String s) {
                baseLayoutBinding.text2.setText(s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("AsyncTaskError", e.getMessage()); // 모든 에러는 구독자의 onError() 함수에서 처리할 수 있어야 한다.
            }

            @Override
            public void onComplete() {
                Log.i("AsyncTaskDone", "done");
            }
        };
    }


}
