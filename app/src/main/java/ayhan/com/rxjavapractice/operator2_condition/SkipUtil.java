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

public class SkipUtil extends AppCompatActivity {

    BaseLayoutBinding baseLayoutBinding;
    private String text1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);
        baseLayoutBinding.info.setText("skipUtil 함수 : takeUtil() 과는 정반대의 함수로 Observable 에서 데이터를 발행할 때까지 값을 건나 뛴다.");

        skipUtil();
    }

    private void skipUtil() {

        String[] data = {RED, YELLOW, SKY, GREEN, BLUE, PUPPLE};

        Observable<String> source = Observable.fromArray(data)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS),
                        (val, unUSed) -> val)
                .skipUntil(Observable.timer(500L, TimeUnit.MILLISECONDS));

        source.subscribe(result -> text1 += result + "\n");

        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();

        baseLayoutBinding.text1.setText(text1);
    }

}
