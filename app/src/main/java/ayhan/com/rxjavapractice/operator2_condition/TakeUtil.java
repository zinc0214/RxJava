package ayhan.com.rxjavapractice.operator2_condition;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.common.CommonUtils;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;

import static ayhan.com.rxjavapractice.common.Shape.BLUE;
import static ayhan.com.rxjavapractice.common.Shape.GREEN;
import static ayhan.com.rxjavapractice.common.Shape.PUPPLE;
import static ayhan.com.rxjavapractice.common.Shape.RED;
import static ayhan.com.rxjavapractice.common.Shape.SKY;
import static ayhan.com.rxjavapractice.common.Shape.YELLOW;

/**
 * Created by HanAYeon on 2018-04-29.
 */

public class TakeUtil extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        baseLayoutBinding.info.setText("takeUtil 함수 : take()함수에 조건을 설정할 수 있다. \n" +
                "인자로 받은 Observable 에서 어떤 값을 발행하면 현재 Observable 의 데이터 발행을 중단하고 즉시 완료한다.\n"+
                "take() 처럼 일정 개수만 값을 발행하되 완료 기준을 다른 Observable 에서 값을 발행하는지로 판단한다.");

        takeUtil();

    }

    private void takeUtil() {
        String[] data = {RED, YELLOW, GREEN, SKY, BLUE, PUPPLE};

        Observable<String> source = Observable.fromArray(data)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS),
                        (val, notUSed) -> val)
                .takeUntil(Observable.interval(500L,TimeUnit.MILLISECONDS));

        source.subscribe(text -> text1 += text + "\n");
        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();

        baseLayoutBinding.text1.setText(text1);
    }

}
