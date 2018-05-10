package ayhan.com.rxjavapractice.androidexam2;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.PollingLayoutBinding;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HanAYeon on 2018. 5. 10..
 */
public class TimerToRxActivity extends AppCompatActivity {

    private PollingLayoutBinding pollingLayoutBinding;

    private static final long INITAL_DELAY = 0L;
    private static final long PERIOD = 3L;

    private LogAdapter logAdapter;
    private List<String> logs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        pollingLayoutBinding = DataBindingUtil.setContentView(this, R.layout.polling_layout);

        pollingLayoutBinding.btnPolling.setOnClickListener(l-> startPollingVer1());
        pollingLayoutBinding.btnPolling2.setOnClickListener(l -> startPollingVer2());

        setupLogger();
    }

    private void startPollingVer1() {

        Observable<String> observable1 = Observable.interval(INITAL_DELAY,PERIOD, TimeUnit.SECONDS) // 3초 마다 정수를 발생하는 Observable 생성
                .flatMap(o -> Observable.just("Polling #1 - " + o.toString())); // flatMap 으로 polling #1 과 발생된 정수 값을 결합하여 새로운 Observable 로 대체한다.

        observable1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())      // 이용한 스레드를 설정한다..
                .subscribe(this::log);
    }

    private void startPollingVer2() {

        Observable<String> observable2 = Observable.just("Polling #2")      // just 함수를 이용하여 문자를 발행한다.
                .repeatWhen(o -> o.delay(PERIOD, TimeUnit.SECONDS));    // repeatWhen 을 사용하여 동일한 Observable객체를 계속 발행하게 설정한다. 3초의 자연을 설정한다.

        observable2.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.getMainLooper())) // AndroidSchedulers.from(Looper.getMainLooper() ==  AndroidSchedulers.mainThread()
                .subscribe(this::log);
    }

    private void log(String log) {
        logs.add(log);
        logAdapter.clear();
        logAdapter.addAll(logs);
    }

    private void setupLogger() {
        logs = new ArrayList<>();
        logAdapter = new LogAdapter(this, new ArrayList<>());
        pollingLayoutBinding.lvPollingLog.setAdapter(logAdapter);
    }

}