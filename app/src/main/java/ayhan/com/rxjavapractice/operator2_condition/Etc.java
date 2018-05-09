package ayhan.com.rxjavapractice.operator2_condition;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.common.CommonUtils;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;
import io.reactivex.schedulers.Timed;

/**
 * Created by HanAYeon on 2018-04-29.
 */

public class Etc extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1 = "";
    private String text2 = "\n\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);
        baseLayoutBinding.info.setText("dalay() :  시간을 인자로 받는 함수. 그 시간 만큼 지연 후 실행한다." +
                "timeInterval() : 어떠한 값을 발행했을 때 이전 값을 발행한 후 얼마나 시간이 흘렀는지 알려준다.");

        delay();
        timeInterval();
    }

    private void delay() {
        String[] data = {"1", "8", "2", "5", "4"};
        Observable<String> source = Observable.fromArray(data)
                .delay(100L, TimeUnit.MILLISECONDS);
        source.subscribe(result -> text1 += result + "\n");
        CommonUtils.sleep(100);

        baseLayoutBinding.text1.setText(text1);
    }

    private void timeInterval() {
        String[] data =  {"1", "3", "7"};

        CommonUtils.exampleStart();
        Observable<Timed<String>> source = Observable.fromArray(data)
                .delay(item -> {
                    CommonUtils.doSomething();
                    return Observable.just(item);
                })
                .timeInterval();


        source.subscribe(result -> text2 += result + "\n");
        CommonUtils.sleep(10000);

    }
}
