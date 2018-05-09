package ayhan.com.rxjavapractice.operator2_create;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.common.CommonUtils;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;

/**
 * Created by HanAYeon on 2018-04-08.
 */

/*
* timer 함수 : interval 함수와 유사하지만 한 번먼 실행된다는 점이 다르다.
* 일정 시간이 지난 후 한 개의 데이터를 발행하고 onComplete() 이벤트가 발생한다.
* */


public class Timer extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String printText= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);
        baseLayoutBinding.info.setText("" +
                "timer 함수 : interval 함수와 유사하지만 한 번먼 실행된다는 점이 다르다.\n" +
                "일정 시간이 지난 후 한 개의 데이터를 발행하고 onComplete() 이벤트가 발생한다.");

        showTime();
    }

    private void showTime() {

        CommonUtils.exampleStart();
        Observable<String> source = Observable.timer(500L, TimeUnit.MILLISECONDS)
                .map(notUsed -> {
                    return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                            .format(new Date());
                });

        source.subscribe(data -> printText += data);
        CommonUtils.sleep(1000);

        baseLayoutBinding.text1.setText(printText);
    }
}
